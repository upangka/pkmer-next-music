package io.gitee.pkmer.minio.service;

import java.util.List;
import java.util.Optional;

/**
 * 文件元数据持久化接口
 */
public interface FileMetaInfoRepository {

    /**
     * 保存文件元数据
     */
    void save(FileMetaInfoDto fileMetaInfoDto);

    void update(FileMetaInfoDto fileMetaInfoDto);

    /**
     * 根据md5加载文件元数据
     * @param md5 文件md5
     */
    List<FileMetaInfoDto> loadByMd5(String md5);

    /**
     * 根据md5加载指定用户上传的文件元数据
     * @param md5 文件md5
     */
    FileMetaInfoDto loadByMd5(String md5,String userId);
}
