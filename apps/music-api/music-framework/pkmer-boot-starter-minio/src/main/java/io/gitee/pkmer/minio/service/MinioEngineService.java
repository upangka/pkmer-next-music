package io.gitee.pkmer.minio.service;

import io.gitee.pkmer.minio.common.StorageBucketEnums;
import io.gitee.pkmer.minio.dto.MergeFileResult;
import io.gitee.pkmer.minio.dto.ObjectNameAndOssPath;
import io.gitee.pkmer.minio.utils.CommonUtil;
import io.gitee.pkmer.minio.utils.FileUtil;
import io.gitee.pkmer.minio.utils.MimeTypeUtil;
import io.gitee.pkmer.minio.utils.UUIDUtil;
import io.gitee.pkmer.security.context.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<FileMetaInfoDto> fileMetaInfos = fileMetaInfoRepository.loadByMd5(fileMd5);

        if (!fileMetaInfos.isEmpty()) {

            for(FileMetaInfoDto fileMetaInfoDto : fileMetaInfos){
                // 1. 处理秒传
                if (fileMetaInfoDto.getIsFinished()) {
                    // 秒传：文件已存在且已完成上传
                    return quickUpload(fileMd5, fileName, fileSize, fileMetaInfoDto);
                }
            }
            // 2. 处理断点续传
            return resumeUpload(fileMetaInfos);

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


        final ObjectNameAndOssPath objectNameAndOssPath = buildObjectNameAndOssPath(fileMetaInfoDto);
        String presignedObjectUrl;
        try {
            // 获取预授权访问链接
            presignedObjectUrl = minioAdapter.getPresignedObjectUrl(
                    fileMetaInfoDto.getBucket(),
                    objectNameAndOssPath.getObjectName());
        } catch (Exception e) {
            log.error("获取预签名URL失败", e);
            throw new RuntimeException(e);
        }

        // 秒传成功之后设置预生成链接和
        MergeFileResult mergeFileResult = MergeFileResult.builder()
                .presignedObjectUrl(presignedObjectUrl)
                .ossPath(objectNameAndOssPath.getOssPath())
                .build();

        FileInitView view = FileInitView.builder()
                .id(fileMetaInfoDto.getId().toString())
                .fileKey(UUIDUtil.getUUID())
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
                .fileMimeType(fileMetaInfoDto.getFileMimeType())
                .mergeFileResult(mergeFileResult)
                .build();

        persistFileMetaInfo(view);
        return view;
    }


    /**
     * 根据文件元信息恢复上传任务
     * 该方法主要用于恢复已存在的上传任务，通过获取已上传的分片信息，并标记这些分片，以便继续未完成的上传任务
     *
     * @return 返回一个FileInitView对象，其中包含了文件的元信息和已上传分片的信息，用于展示文件上传的初始化视图
     */
    private FileInitView resumeUpload(List<FileMetaInfoDto> metaInfos) {

        String currentUploadUserId = AppContextHolder.userContextHolder.getUser().getId().toString();

        Optional<FileMetaInfoDto> currentUserUpload = metaInfos.stream()
                .filter(m -> m.getCreateUser().equals(currentUploadUserId))
                .findFirst();


        boolean isCurrentUserUpload = currentUserUpload.isPresent();

        if(isCurrentUserUpload){
            // 用户自己上传的
            FileMetaInfoDto metaInfo = currentUserUpload.get();
            return buildResumeDownload(metaInfo);
        }else{
            // 使用其他用户上传的文件
            Supplier<FileMetaInfoDto> findUnFinishedFileMetaInfoDto = () -> metaInfos.stream()
                    .filter(m -> !m.getIsFinished())
                    .findFirst()
                    .orElseThrow();

            FileMetaInfoDto metaInfo = metaInfos.stream()
                    .filter(FileMetaInfoDto::getIsFinished)
                    .findAny()
                    .orElseGet(findUnFinishedFileMetaInfoDto);


            // 保存用户上传的文件的元数据信息
            metaInfo.setId(null);
            metaInfo.setCreateTime(LocalDateTime.now());
            metaInfo.setCreateUser(currentUploadUserId);
            fileMetaInfoRepository.save(metaInfo);

            return buildResumeDownload(metaInfo);
        }
    }

    /**
     * 构建用户继续上传的文件的下载视图
     */
    private FileInitView buildResumeDownload(FileMetaInfoDto metaInfo) {
        List<FileInitView.Part> unUploadedParts = generateUploadShardingParts(metaInfo);
        return FileInitView.builder()
                .id(metaInfo.getId().toString())
                .fileKey(metaInfo.getFileKey())
                .fileMd5(metaInfo.getFileMd5())
                .fileName(metaInfo.getFileName())
                .fileMimeType(metaInfo.getFileMimeType())
                .fileSuffix(metaInfo.getFileSuffix())
                .fileSize(metaInfo.getFileSize())
                .bucket(metaInfo.getBucket())
                .bucketPath(metaInfo.getBucketPath())
                .uploadId(metaInfo.getUploadId())
                .isFinished(metaInfo.getIsFinished())
                .partNumber(metaInfo.getPartNumber())
                .parts(unUploadedParts)
                .build();
    }


    /**
     * 找到已上传的分片信息
     * @param metaInfo 用户上传的文件的元数据信息
     * @return 已上传的分片的信息
     */
    private List<PartSummary> findUploadedParts(FileMetaInfoDto metaInfo) {
        // 获取已上传的分片信息
       return  minioAdapter.listParts(
                metaInfo.getBucket(),
                CommonUtil.getObjectName(metaInfo.getFileMd5()),
                metaInfo.getUploadId(),
                metaInfo.getPartNumber()
        );
    }


    /**
     * 生成未上传的分片信息
     * @param metaInfo 用户上传的文件的元数据信息
     * @return 未上传的分片的信息
     */
    private List<FileInitView.Part> generateUploadShardingParts(FileMetaInfoDto metaInfo){

        List<PartSummary> uploadedParts = findUploadedParts(metaInfo);

        // 找到丢失的分片
        List<Integer> unUploadPartNumber = findUnuploadPartNumber(metaInfo,uploadedParts);

        final String objectName = CommonUtil.getObjectName(metaInfo.getFileMd5());
        List<CompletableFuture<FileInitView.Part>> allFutureParts = new ArrayList<>();
        for(Integer partNumber : unUploadPartNumber){
            // 分片序号以1开始
            final long start = (partNumber - 1) * bigFileHelper.getChunkSize();
            CompletableFuture<FileInitView.Part> futurePart = getPartCompletableFuture(
                    metaInfo.getBucket(),
                    objectName,
                    metaInfo.getUploadId(),
                    partNumber,
                    start,
                    metaInfo.getFileSize());

            allFutureParts.add(futurePart);
        }

       return allFutureParts.stream()
                .map(CompletableFuture::join) // join() 抛出的异常是未检查异常，减少了异常处理的代码
                .toList();
    }

    /**
     * 找到丢失的分片序号
     * @param metaInfo 文件元数据信息
     * @param uploadedParts 已经上传的分片信息
     */
    private List<Integer> findUnuploadPartNumber(FileMetaInfoDto metaInfo, List<PartSummary> uploadedParts) {

        Set<Integer> uploadedPartNumberSet = uploadedParts.stream()
                .map(PartSummary::getPartNumber)
                .collect(Collectors.toSet());

        // 丢失的分片序号
        List<Integer> lostPartNumbers = new ArrayList<>();
        // 分片编号以1开始
        for(int i = 1; i <= metaInfo.getPartNumber(); i++){
            if(!uploadedPartNumberSet.contains(i)){
                lostPartNumbers.add(i);
            }
        }

        return lostPartNumbers;
    }

    @NotNull
    private static Supplier<FileMetaInfoDto> getFileMetaInfoDtoSupplier(List<FileMetaInfoDto> metaInfos) {
        return () -> metaInfos.stream()
                .filter(m -> !m.getIsFinished())
                .findFirst()
                .orElseThrow();
    }


    /**
     * 将FileInitView对象持久化到数据库中
     */
    private void persistFileMetaInfo(FileInitView fileInitView) {
//        Long userId = AppContextHolder.userContextHolder.getUser().getId();
        // TODO 登录完成之后，处理用户id
        Long userId = 1L;
        FileMetaInfoDto dto = toFileMetaInfoDto(fileInitView);
        addShardingInitFileMetaInfo(dto, userId);

        fileMetaInfoRepository.save(dto);
        fileInitView.setId(dto.getId().toString());
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
        // 优化文件名路径+文件名，不包括bucketName 如：2025/02/黄昏 (女声版)(DJ歌者达达版) - Kag Chuu.flac
//        String objectName = CommonUtil.getObjectName(fileMd5);
        String objectName = CommonUtil.getObjectName(fileName);
        String filePath = CommonUtil.getPathByDate();

        minioAdapter.createBucket(bucketName);
        // 上传多文件的id
        String uploadId = minioAdapter.createMultipartUpload(bucketName, objectName, mimeType);

        /**
         * 文件分片的总数
         */
        int chunkSize = bigFileHelper.computeChunks(fileSize);
        long start = 0L;
        List<FileInitView.Part> parts = generateShardingParts(
                chunkSize,
                start,
                bucketName,
                objectName,
                uploadId,
                fileSize);

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
                .partNumber(chunkSize)
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
                                                          final String uploadId,
                                                          final long fileSize) {


        List<CompletableFuture<FileInitView.Part>> futureParts = new ArrayList<>();

        for (int partNumber = 1; partNumber <= chunckTotal; partNumber++) {

            CompletableFuture<FileInitView.Part> futurePart = getPartCompletableFuture(
                    bucketName,
                    objectName,
                    uploadId,
                    partNumber,
                    start,
                    fileSize);

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

    @NotNull
    private CompletableFuture<FileInitView.Part> getPartCompletableFuture(
            final String bucketName,
            final String objectName,
            final String uploadId,
            final int finalPartNumber,
            final long finalStart,
            final long fileSize) {

        return  CompletableFuture.supplyAsync(() -> {
            // 异步创建上传URL
            final String uploadUrl = minioAdapter.createUploadUrl(
                    bucketName,
                    objectName,
                    uploadId,
                    String.valueOf(finalPartNumber));


            long end = Math.min(fileSize,finalStart + bigFileHelper.getChunkSize());

            // 创建文件分片信息
            return FileInitView.Part.builder()
                    .uploadId(uploadId)
                    .uploadUrl(uploadUrl)
                    .partNumber(finalPartNumber)
                    .shardingStart(finalStart)
                    .shardingEnd(end)
                    .build();
        });
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
     * 通过文件元数据信息meta data
     * 构建文件对象名称
     * @return 文件对象名称
     */
    private ObjectNameAndOssPath buildObjectNameAndOssPath(FileMetaInfoDto metaInfo){
        // 注意因为接下来会拿所有上传分片的信息，所以这里objectName要和创建分片的时候一样。
        String objectName = metaInfo.getBucketPath() + "/" + metaInfo.getFileName();
        String ossPath = "/"+ metaInfo.getBucket()  + "/" + objectName;
        return ObjectNameAndOssPath.builder()
                .objectName(objectName)
                .ossPath(ossPath)
                .build();
    }

    /**
     * 合并分片并完成上传
     * @param fileMd5 文件MD5
     * @param partMd5List 分片MD5列表
     * @param userId 用户ID
     * @return 合并后的文件访问URL
     */
    public MergeFileResult mergeFile(String fileMd5, List<String> partMd5List, String userId) {

        // 1. 获取文件元信息
        FileMetaInfoDto metaInfo = fileMetaInfoRepository.loadByMd5(fileMd5,userId);
        // 注意因为接下来会拿所有上传分片的信息，所以这里objectName要和创建分片的时候一样。
        ObjectNameAndOssPath objectNameAndOssPath = buildObjectNameAndOssPath(metaInfo);

        String objectName = objectNameAndOssPath.getObjectName();
        try {
            // 2. 验证分片数量
            if (partMd5List.size() != metaInfo.getPartNumber()) {
                throw new RuntimeException("Part number mismatch");
            }

            // 3. 获取已上传的分片信息
            List<PartSummary> uploadedParts = minioAdapter.listParts(
                    metaInfo.getBucket(),
                    objectName,
//                    CommonUtil.getObjectName(metaInfo.getFileMd5()),
                    metaInfo.getUploadId(),
                    metaInfo.getPartNumber()
            );

            // 4. 验证所有分片都已上传
            if (uploadedParts.size() != metaInfo.getPartNumber()) {
                throw new RuntimeException("Some parts are missing");
            }

            // 5. 合并分片
            String etag = minioAdapter.completeMultipartUpload(
                    metaInfo.getBucket(),
                    objectName,
                    // CommonUtil.getObjectName(metaInfo.getFileMd5()), // todo 用bucket objectname 替换
                    metaInfo.getUploadId(),
                    uploadedParts
            );

            // 6. 更新文件状态
            updateFileMetaInfo(metaInfo, etag,true);



            // 7. 生成文件访问URL
            String presignedObjectUrl = minioAdapter.getPresignedObjectUrl(
                    metaInfo.getBucket(),
                    objectName
                    //CommonUtil.getObjectName(metaInfo.getFileMd5())
            );

            return MergeFileResult.builder()
                    .ossPath(objectNameAndOssPath.getOssPath())
                    .presignedObjectUrl(presignedObjectUrl)
                    .build();

        } catch (Exception e) {
            // 合并失败时中止上传
            minioAdapter.abortMultipartUpload(
                    metaInfo.getBucket(),
                    objectName,
                    //CommonUtil.getObjectName(metaInfo.getFileMd5()),
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
     * @param isFinished 文件是否上传完成的状态，true表示完成，false表示未完成
     */
    private void updateFileMetaInfo(FileMetaInfoDto metaInfo, String etag,boolean isFinished) {
        metaInfo.setIsFinished(isFinished);
        metaInfo.setUpdateTime(LocalDateTime.now());
        metaInfo.setEtag(etag);
        fileMetaInfoRepository.update(metaInfo);
    }

    /**
     * 重试上传失败的分片
     * @param fileMd5 文件MD5
     * @param partNumber 分片号
     * @param userId 用户ID
     *
     * @return 新的上传URL
     */
    public String retryUploadPart(String fileMd5, int partNumber,String userId) {
        FileMetaInfoDto metaInfo = fileMetaInfoRepository.loadByMd5(fileMd5,userId);

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


    /**
     * 计算预分片的信息
     * @param fileSize 文件大小
     */
    public ShardingView sharding(Long fileSize){
        int totalChunk = bigFileHelper.computeChunks(fileSize);
        long chunkSize = bigFileHelper.getChunkSize();

        List<ShardingView.Part> parts = new ArrayList<>();
        for(int i = 1; i <= totalChunk; i++){
            long start = (i - 1) * chunkSize;
            long end = Math.min(fileSize,i * chunkSize);
            ShardingView.Part part = ShardingView.Part.builder()
                    .partNumber(i)
                    .start(start)
                    .end(end)
                    .build();
            parts.add(part);
        }

        return ShardingView.builder()
                .totalParts(totalChunk)
                .fileSize(fileSize)
                .chunkSize(chunkSize)
                .parts(parts)
                .build();

    }
}
