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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        Optional<FileMetaInfoDto> fileMetaInfoDtoOptional = fileMetaInfoRepository.loadByMd5(fileMd5);

        if (fileMetaInfoDtoOptional.isPresent()) {
            // 1. 处理秒传
            FileMetaInfoDto fileMetaInfoDto = fileMetaInfoDtoOptional.get();
            if (fileMetaInfoDto.getIsFinished()) {
                return null;
            } else {
                // 2. 处理断点续传
                return null;
            }
        } else {
            // 3. 用户第一次上传
            FileInitView fileInitView = handleUserFirstUpload(fileMd5, fileName, fileSize);
            persistFileMetaInfo(fileInitView);
            return fileInitView;
        }
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
        return  FileMetaInfoDto.builder()
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
//        CreateUploadUrlReq createUploadUrlReq = CreateUploadUrlReq.builder()
//                .fileMd5(fileMd5)
//                .fullFileName(fileName)
//                .fileSize(fileSize)
//                .isResumableUpload(false)
//                .build();


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
}
