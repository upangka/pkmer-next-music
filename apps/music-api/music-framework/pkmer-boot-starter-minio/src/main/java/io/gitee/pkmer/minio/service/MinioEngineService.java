package io.gitee.pkmer.minio.service;

/**
 * 处理大文件的分片上传的服务
 * 1. 秒传
 * 2. 分片上传
 * 3. 合并分片
 */
public class MinioEngineService {
    private final FileMetaInfoRepository fileMetaInfoRepository;
    public MinioEngineService(FileMetaInfoRepository fileMetaInfoRepository){
        this.fileMetaInfoRepository = fileMetaInfoRepository;
    }

    /**
     * 初始化文件信息
     * 该方法用于设置文件的MD5值、名称和大小，以便在后续操作中使用这些信息
     *
     * @param fileMd5 文件的MD5值，用于唯一标识文件内容
     * @param fileName 文件名，用于标识文件
     * @param fileSize 文件大小，用于表示文件的字节大小
     */

    public void init(String fileMd5,String fileName,String fileSize){
    }
}
