package io.gitee.pkmer.minio.repository.mybatis;

import io.gitee.pkmer.minio.repository.FileMetaInfoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FileMetaInfoRepositoryImpl implements FileMetaInfoRepository {
    private final FileMetadataInfoMapper fileMetadataInfoMapper;
    public FileMetaInfoRepositoryImpl(FileMetadataInfoMapper fileMetadataInfoMapper){
        this.fileMetadataInfoMapper = fileMetadataInfoMapper;
    }

}
