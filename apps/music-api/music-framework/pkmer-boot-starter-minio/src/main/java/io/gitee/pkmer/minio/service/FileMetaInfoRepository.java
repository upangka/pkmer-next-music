package io.gitee.pkmer.minio.service;

/**
 * 文件元数据持久化接口
 */
public interface FileMetaInfoRepository {

    void save(FileMetaInfoDto fileMetaInfoDto);
}
