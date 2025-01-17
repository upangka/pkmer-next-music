package io.gitee.pkmer.minio.repository.mybatis;

import io.gitee.pkmer.minio.service.FileMetaInfoDto;
import io.gitee.pkmer.minio.service.FileMetaInfoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static io.gitee.pkmer.minio.repository.mybatis.FileMetadataInfoDynamicSqlSupport.*;

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

    @Override
    public Optional<FileMetaInfoDto> loadByMd5(String md5) {

//       select(fileMetadataInfo.allColumns())
//                .from(fileMetadataInfo)
//                .where(fileMd5, isEqualTo(md5))
//                .build()
//                .render(RenderingStrategies.MYBATIS3);

        Optional<FileMetadataInfo> optionalFileMetadataInfo = fileMetadataInfoMapper.selectOne(c ->
                c.where(fileMd5, isEqualTo(md5)));

        if(optionalFileMetadataInfo.isEmpty()){
            return Optional.empty();
        }else{
            FileMetadataInfo fileMetadataInfo = optionalFileMetadataInfo.get();
            return Optional.of(Converter.INSTANCE.toDto(fileMetadataInfo));
        }
    }

    @Mapper
    public interface Converter{
        Converter INSTANCE = Mappers.getMapper(Converter.class);
        FileMetaInfoDto toDto(FileMetadataInfo fileMetadataInfo);
    }
}
