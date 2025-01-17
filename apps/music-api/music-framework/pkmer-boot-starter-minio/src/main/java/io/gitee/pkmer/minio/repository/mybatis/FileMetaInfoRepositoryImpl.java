package io.gitee.pkmer.minio.repository.mybatis;

import io.gitee.pkmer.minio.service.FileMetaInfoDto;
import io.gitee.pkmer.minio.service.FileMetaInfoRepository;

public class FileMetaInfoRepositoryImpl implements FileMetaInfoRepository {
    private final FileMetadataInfoMapper fileMetadataInfoMapper;
    public FileMetaInfoRepositoryImpl(FileMetadataInfoMapper fileMetadataInfoMapper){
        this.fileMetadataInfoMapper = fileMetadataInfoMapper;
    }

    @Override
    public void save(FileMetaInfoDto fileMetaInfoDto) {
        FileMetadataInfo fileMetadataInfo = new FileMetadataInfo();
        fileMetadataInfoMapper.insert(fileMetadataInfo);
    }
}
