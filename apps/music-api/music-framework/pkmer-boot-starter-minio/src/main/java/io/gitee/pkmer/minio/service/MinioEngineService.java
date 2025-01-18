package io.gitee.pkmer.minio.service;

import io.gitee.pkmer.minio.common.StorageBucketEnums;
import io.gitee.pkmer.minio.utils.CommonUtil;
import io.gitee.pkmer.minio.utils.FileUtil;
import io.gitee.pkmer.minio.utils.MimeTypeUtil;
import io.gitee.pkmer.minio.utils.UUIDUtil;
import io.gitee.pkmer.security.context.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * 处理大文件的分片上传的服务
 * 1. 秒传
 * 2. 分片上传
 * 3. 合并分片
 */
@Slf4j
public class MinioEngineService {
    private final FileMetaInfoRepository fileMetaInfoRepository;
    private final MinioAdapter minioAdapter;
    private final BigFileHelper bigFileHelper;

    public MinioEngineService(MinioAdapter minioAdapter,
                              FileMetaInfoRepository fileMetaInfoRepository,
                              BigFileHelper bigFileHelper) {
        this.fileMetaInfoRepository = fileMetaInfoRepository;
        this.minioAdapter = minioAdapter;
        this.bigFileHelper = bigFileHelper;
    }

    /**
     * 初始化文件信息
     * 该方法用于设置文件的MD5值、名称和大小，以便在后续操作中使用这些信息
     *
     * @param fileMd5  文件的MD5值，用于唯一标识文件内容
     * @param fileName 文件名，用于标识文件
     * @param fileSize 文件大小，用于表示文件的字节大小
     */

    public FileInitView init(String fileMd5, String fileName, Long fileSize) {

        // todo 判断list
        Optional<FileMetaInfoDto> fileMetaInfoDtoOptional = fileMetaInfoRepository.loadByMd5(fileMd5);

        if (fileMetaInfoDtoOptional.isPresent()) {
            // 1. 处理秒传
            FileMetaInfoDto fileMetaInfoDto = fileMetaInfoDtoOptional.get();
            if (fileMetaInfoDto.getIsFinished()) {
                // 秒传：文件已存在且已完成上传
                return quickUpload(fileMd5, fileName, fileSize, fileMetaInfoDto);
            } else {
                // 2. 处理断点续传
                return resumeUpload(fileMetaInfoDto);
            }
        } else {
            // 3. 用户第一次上传
            FileInitView fileInitView = handleUserFirstUpload(fileMd5, fileName, fileSize);
            persistFileMetaInfo(fileInitView);
            return fileInitView;
        }
    }

    /**
     * 处理秒传
     * @param fileMd5 文件MD5
     * @param fileName 文件名
     * @param fileSize 文件size
     * @param fileMetaInfoDto 文件元信息
     */
    private FileInitView quickUpload(String fileMd5, String fileName, Long fileSize, FileMetaInfoDto fileMetaInfoDto) {
        FileInitView view = FileInitView.builder()
                .id(fileMetaInfoDto.getId())
                .fileKey(fileMetaInfoDto.getFileKey())
                .fileMd5(fileMd5)
                .fileName(fileName)
                .fileSize(fileSize)
                .isFinished(fileMetaInfoDto.getIsFinished())
                .partNumber(fileMetaInfoDto.getPartNumber())
                .bucket(fileMetaInfoDto.getBucket())
                .bucketPath(fileMetaInfoDto.getBucketPath())
                .uploadId(fileMetaInfoDto.getUploadId())
                .isFinished(fileMetaInfoDto.getIsFinished())
                .createTime(fileMetaInfoDto.getCreateTime())
                .fileSuffix(fileMetaInfoDto.getFileSuffix())
                .fileMimeType(MimeTypeUtil.getMimeType(fileName))
                .build();

        persistFileMetaInfo(view);
        return view;
    }


    /**
     * 根据文件元信息恢复上传任务
     * 该方法主要用于恢复已存在的上传任务，通过获取已上传的分片信息，并标记这些分片，以便继续未完成的上传任务
     *
     * @param metaInfo 文件元信息，包含文件的详细属性，如桶名称、文件MD5、上传ID等
     * @return 返回一个FileInitView对象，其中包含了文件的元信息和已上传分片的信息，用于展示文件上传的初始化视图
     */
    private FileInitView resumeUpload(FileMetaInfoDto metaInfo) {

        boolean isSelf = isCurrentUserUpload(metaInfo.getCreateUser());

        // 获取已上传的分片信息
        List<PartSummary> uploadedParts = minioAdapter.listParts(
                metaInfo.getBucket(),
                CommonUtil.getObjectName(metaInfo.getFileMd5()),
                metaInfo.getUploadId()
        );

        // 生成所有分片信息
        List<FileInitView.Part> allParts = generateShardingParts(
                metaInfo.getPartNumber(),
                0L,
                metaInfo.getBucket(),
                CommonUtil.getObjectName(metaInfo.getFileMd5()),
                metaInfo.getUploadId()
        );

        // 使用Map来存储已上传的分片信息，提高查找效率
        Map<Integer, String> uploadedPartsMap = uploadedParts.stream()
                .collect(Collectors.toMap(
                        PartSummary::getPartNumber,
                        PartSummary::getEtag,
                        (existing, replacement) -> {
                            log.warn("发现重复的分片号: {}", existing);
                            return existing;
                        }
                ));

        // 更新分片状态
        allParts.forEach(part -> {
            Integer partNumber = part.getPartNumber();
            if (uploadedPartsMap.containsKey(partNumber)) {
                part.setUploaded(true);
                part.setEtag(uploadedPartsMap.get(partNumber));
            }
        });

        List<FileInitView.Part> unUploadedParts = allParts.stream().filter(part -> !part.isUploaded()).toList();

        return FileInitView.builder()
                .id(metaInfo.getId())
                .fileKey(metaInfo.getFileKey())
                .fileMd5(metaInfo.getFileMd5())
                .fileName(metaInfo.getFileName())
                .fileMimeType(metaInfo.getFileMimeType())
                .fileSuffix(metaInfo.getFileSuffix())
                .fileSize(metaInfo.getFileSize())
                .bucket(metaInfo.getBucket())
                .bucketPath(metaInfo.getBucketPath())
                .uploadId(metaInfo.getUploadId())
                .isFinished(false)
                .partNumber(metaInfo.getPartNumber())
                .parts(unUploadedParts)
                .build();
    }

    /**
     * 判断是否问当前用户上传的文件
     */

    private  boolean isCurrentUserUpload(String uploadUserId) {
        Long currentUserId = AppContextHolder.userContextHolder.getUser().getId();
        return uploadUserId.equals(currentUserId.toString());
    }

    /**
     * 将FileInitView对象持久化到数据库中
     */
    private void persistFileMetaInfo(FileInitView fileInitView) {
        Long userId = AppContextHolder.userContextHolder.getUser().getId();

        FileMetaInfoDto dto = toFileMetaInfoDto(fileInitView);
        addShardingInitFileMetaInfo(dto, userId);

        fileMetaInfoRepository.save(dto);
        fileInitView.setId(dto.getId());
    }

    /**
     * 将FileInitView对象转换为FileMetaInfoDto对象
     */
    private static FileMetaInfoDto toFileMetaInfoDto(FileInitView fileInitView) {
        return FileMetaInfoDto.builder()
                .bucket(fileInitView.getBucket())
                .fileKey(fileInitView.getFileKey())
                .fileMd5(fileInitView.getFileMd5())
                .fileName(fileInitView.getFileName())
                .fileMimeType(fileInitView.getFileMimeType())
                .fileSuffix(fileInitView.getFileSuffix())
                .fileSize(fileInitView.getFileSize())
                .bucket(fileInitView.getBucket())
                .bucketPath(fileInitView.getBucketPath())
                .uploadId(fileInitView.getUploadId())
                .isFinished(fileInitView.isFinished())
                .partNumber(fileInitView.getPartNumber())
                .build();
    }

    /**
     * 设置文件初始化信息
     */
    private static void addShardingInitFileMetaInfo(FileMetaInfoDto dto, Long userId) {
        dto.setIsPart(true);
        dto.setIsPreview(false);
        dto.setIsPrivate(false);
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateTime(LocalDateTime.now());
        dto.setUpdateUser(userId.toString());
        dto.setCreateUser(userId.toString());
    }

    /**
     * 处理用户首次上传文件的请求
     * 此方法用于初始化文件上传的相关信息，并计算大文件上传的分片数量
     *
     * @param fileMd5  文件的MD5哈希值，用于唯一标识文件
     * @param fileName 文件名，包括文件的完整路径
     * @param fileSize 文件大小，用于计算上传分片的总数
     */
    private FileInitView handleUserFirstUpload(String fileMd5, String fileName, Long fileSize) {

        String fileExtension = FileUtil.getFileExtension(fileName);
        if (fileExtension.isBlank()) {
            throw new RuntimeException("文件后缀不能为空");
        }
        String bucketName = getBucketName(fileName);
        String fileKey = UUIDUtil.getUUID();
        String mimeType = MimeTypeUtil.getMimeType(fileName);
        String objectName = CommonUtil.getObjectName(fileMd5);
        String filePath = CommonUtil.getPathByDate();

        minioAdapter.createBucket(bucketName);
        String uploadId = minioAdapter.createMultipartUpload(bucketName, objectName, mimeType);

        /**
         * 文件分片的总数
         */
        int chunckTotal = bigFileHelper.computeChunks(fileSize);
        long start = 0L;
        List<FileInitView.Part> parts = generateShardingParts(
                chunckTotal,
                start,
                bucketName,
                objectName,
                uploadId);

        return FileInitView.builder()
                .fileKey(fileKey)
                .fileMd5(fileMd5)
                .fileName(fileName)
                .fileMimeType(mimeType)
                .fileSuffix(fileExtension)
                .fileSize(fileSize)
                .bucket(bucketName)
                .bucketPath(filePath)
                .uploadId(uploadId)
                .isFinished(false)
                .partNumber(chunckTotal)
                .parts(parts)
                .build();
    }

    /**
     * 生成分片上传的各个部分信息
     *
     * @param chunckTotal 总分片数量，决定文件被分成多少部分进行上传
     * @param start       文件上传的起始字节位置
     * @param bucketName  存储桶名称，标识文件存储的位置
     * @param objectName  对象名称，即文件在存储桶中的名称
     * @param uploadId    多部分上传的唯一标识符，用于跟踪和管理上传过程
     * @return 返回一个包含多个FileInitView.Part对象的列表，每个对象代表一个分片的部分信息
     */
    @NotNull
    private List<FileInitView.Part> generateShardingParts(final int chunckTotal,
                                                          long start,
                                                          final String bucketName,
                                                          final String objectName,
                                                          final String uploadId) {


        List<CompletableFuture<FileInitView.Part>> futureParts = new ArrayList<>();

        for (int partNumber = 1; partNumber <= chunckTotal; partNumber++) {

            final int finalPartNumber = partNumber;
            final long finalStart = start;
            CompletableFuture<FileInitView.Part> futurePart = CompletableFuture.supplyAsync(() -> {
                // 异步创建上传URL
                final String uploadUrl = minioAdapter.createUploadUrl(
                        bucketName,
                        objectName,
                        uploadId,
                        String.valueOf(finalPartNumber));

                // 创建文件分片信息
                return FileInitView.Part.builder()
                        .uploadId(uploadId)
                        .uploadUrl(uploadUrl)
                        .partNumber(finalPartNumber)
                        .shardingStart(finalStart)
                        .shardingEnd(finalStart + bigFileHelper.getChunkSize())
                        .build();
            });

            futureParts.add(futurePart);

            // JavaScript中File对象的slice方法区间是[start, end)，即包括开始索引start，但不包括结束索引end。
            start += bigFileHelper.getChunkSize();
        }

        // 等待所有异步任务完成
        List<FileInitView.Part> parts = new ArrayList<>();
        for (CompletableFuture<FileInitView.Part> future : futureParts) {
            try {
                parts.add(future.get()); // 获取每个任务的结果
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return parts;
    }


    /**
     * 根据文件名获取存储桶名称
     *
     * @param fileName 文件名，用于确定文件的存储桶
     * @return 返回与文件后缀对应的存储桶名称
     */
    private String getBucketName(String fileName) {
        String fileExtension = FileUtil.getFileExtension(fileName);
        String bucketName = StorageBucketEnums.getBucketNameByFileSuffix(fileExtension);
        log.info("根据文件后缀获取存储桶名称:{}", bucketName);
        return bucketName;
    }

    /**
     * 合并分片并完成上传
     * @param fileMd5 文件MD5
     * @param partMd5List 分片MD5列表
     * @return 合并后的文件访问URL
     */
    public String mergeFile(String fileMd5, List<String> partMd5List) {
        // 1. 获取文件元信息
        FileMetaInfoDto metaInfo = fileMetaInfoRepository.loadByMd5(fileMd5)
                .orElseThrow(() -> new RuntimeException("File meta info not found"));

        try {
            // 2. 验证分片数量
            if (partMd5List.size() != metaInfo.getPartNumber()) {
                throw new RuntimeException("Part number mismatch");
            }

            // 3. 获取已上传的分片信息
            List<PartSummary> uploadedParts = minioAdapter.listParts(
                    metaInfo.getBucket(),
                    CommonUtil.getObjectName(metaInfo.getFileMd5()),
                    metaInfo.getUploadId()
            );

            // 4. 验证所有分片都已上传
            if (uploadedParts.size() != metaInfo.getPartNumber()) {
                throw new RuntimeException("Some parts are missing");
            }

            // 5. 合并分片
            String etag = minioAdapter.completeMultipartUpload(
                    metaInfo.getBucket(),
                    CommonUtil.getObjectName(metaInfo.getFileMd5()),
                    metaInfo.getUploadId(),
                    uploadedParts
            );

            // 6. 更新文件状态
            updateFileMetaInfo(metaInfo, etag);

            // 7. 生成文件访问URL
            return minioAdapter.getPresignedObjectUrl(
                    metaInfo.getBucket(),
                    CommonUtil.getObjectName(metaInfo.getFileMd5())
            );

        } catch (Exception e) {
            // 合并失败时中止上传
            minioAdapter.abortMultipartUpload(
                    metaInfo.getBucket(),
                    CommonUtil.getObjectName(metaInfo.getFileMd5()),
                    metaInfo.getUploadId()
            );
            throw new RuntimeException("Failed to merge file parts", e);
        }
    }

    /**
     * 更新文件元信息
     *
     * 此方法用于在文件上传完成后更新文件的元信息，包括设置文件上传完成状态、更新时间、以及Amazon S3对象的ETag
     *
     * @param metaInfo 文件元信息对象，包含文件的各类元数据
     * @param etag 文件在Amazon S3上的ETag，用于标识文件的唯一性
     */
    private void updateFileMetaInfo(FileMetaInfoDto metaInfo, String etag) {
        metaInfo.setIsFinished(true);
        metaInfo.setUpdateTime(LocalDateTime.now());
        metaInfo.setEtag(etag);
        fileMetaInfoRepository.save(metaInfo);
    }

    /**
     * 重试上传失败的分片
     * @param fileMd5 文件MD5
     * @param partNumber 分片号
     * @return 新的上传URL
     */
    public String retryUploadPart(String fileMd5, int partNumber) {
        FileMetaInfoDto metaInfo = fileMetaInfoRepository.loadByMd5(fileMd5)
                .orElseThrow(() -> new RuntimeException("File meta info not found"));

        if (metaInfo.getIsFinished()) {
            throw new RuntimeException("File upload already completed");
        }

        return minioAdapter.createUploadUrl(
                metaInfo.getBucket(),
                CommonUtil.getObjectName(metaInfo.getFileMd5()),
                metaInfo.getUploadId(),
                String.valueOf(partNumber)
        );
    }
}
