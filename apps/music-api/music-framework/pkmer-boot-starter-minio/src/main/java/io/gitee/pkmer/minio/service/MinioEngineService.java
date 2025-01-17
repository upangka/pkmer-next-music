package io.gitee.pkmer.minio.service;

import io.gitee.pkmer.minio.common.StorageBucketEnums;
import io.gitee.pkmer.minio.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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

    public void init(String fileMd5, String fileName, Long fileSize) {
        Optional<FileMetaInfoDto> fileMetaInfoDtoOptional = fileMetaInfoRepository.loadByMd5(fileMd5);

        if (fileMetaInfoDtoOptional.isPresent()) {
            // 1. 处理秒传
            FileMetaInfoDto fileMetaInfoDto = fileMetaInfoDtoOptional.get();
            if (fileMetaInfoDto.getIsFinished()) {
                return;
            } else {
                // 2. 处理断点续传
            }
        } else {
            // 3. 用户第一次上传
            handleUserFirstUpload(fileMd5, fileName, fileSize);

        }


    }

    /**
     * 处理用户首次上传文件的请求
     * 此方法用于初始化文件上传的相关信息，并计算大文件上传的分片数量
     *
     * @param fileMd5  文件的MD5哈希值，用于唯一标识文件
     * @param fileName 文件名，包括文件的完整路径
     * @param fileSize 文件大小，用于计算上传分片的总数
     */
    private void handleUserFirstUpload(String fileMd5, String fileName, Long fileSize) {
        CreateUploadUrlReq createUploadUrlReq = CreateUploadUrlReq.builder()
                .fileMd5(fileMd5)
                .fullFileName(fileName)
                .fileSize(fileSize)
                .isResumableUpload(false)
                .build();

        /**
         * 文件分片的总数
         */
        int chunckTotal = bigFileHelper.computeChunks(fileSize);
        String bucketName = getBucketName(fileName);

        minioAdapter.createBucket(bucketName);

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
