package io.gitee.pkmer.minio.service;

import java.util.Optional;

/**
 * 文件元数据持久化接口
 */
public interface FileMetaInfoRepository {

    /**
     * 保存文件元数据
     */
    void save(FileMetaInfoDto fileMetaInfoDto);

    /**
     * 根据md5加载文件元数据
     * @param md5
     */
    Optional<FileMetaInfoDto> loadByMd5(String md5);
}
