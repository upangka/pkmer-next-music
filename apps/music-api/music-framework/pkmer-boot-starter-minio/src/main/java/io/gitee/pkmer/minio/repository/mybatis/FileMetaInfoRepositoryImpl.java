package io.gitee.pkmer.minio.repository.mybatis;

import io.gitee.pkmer.minio.service.FileMetaInfoDto;
import io.gitee.pkmer.minio.service.FileMetaInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static io.gitee.pkmer.minio.repository.mybatis.FileMetadataInfoDynamicSqlSupport.*;

@Slf4j
public class FileMetaInfoRepositoryImpl implements FileMetaInfoRepository {
    private final FileMetadataInfoMapper fileMetadataInfoMapper;

    public FileMetaInfoRepositoryImpl(FileMetadataInfoMapper fileMetadataInfoMapper) {
        this.fileMetadataInfoMapper = fileMetadataInfoMapper;
    }

    @Override
    public void save(FileMetaInfoDto fileMetaInfoDto) {
        FileMetadataInfo fileMetadataInfo = Converter.INSTANCE.toRecord(fileMetaInfoDto);
        fileMetadataInfoMapper.insert(fileMetadataInfo);
        // todo 检查id是否生成
        log.info("检查id是否生成 {}", fileMetadataInfo.getId());
        fileMetadataInfo.setId(fileMetadataInfo.getId());
    }

    @Override
    public void update(FileMetaInfoDto fileMetaInfoDto) {
        FileMetadataInfo record = Converter.INSTANCE.toRecord(fileMetaInfoDto);
        fileMetadataInfoMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public List<FileMetaInfoDto> loadByMd5(String md5) {
        /**
         * 因为秒传，相同的文件也会有相同的md5,所以会有多个结果
         */
        List<FileMetadataInfo> results = fileMetadataInfoMapper.select(
                c -> c.where(fileMd5, isEqualTo(md5)));

        return results.stream().map(Converter.INSTANCE::toDto).toList();
    }

    @Override
    public FileMetaInfoDto loadByMd5(String md5, String userId) {

        FileMetadataInfo record = fileMetadataInfoMapper.selectOne(
                        c -> c.where(fileMd5, isEqualTo(md5))
                                .and(createUser, isEqualTo(userId)))
                .orElseThrow(() -> new RuntimeException("用户" + userId + "没有上传过文件"));

        return Converter.INSTANCE.toDto(record);

    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        FileMetaInfoDto toDto(FileMetadataInfo fileMetadataInfo);

        FileMetadataInfo toRecord(FileMetaInfoDto fileMetaInfoDto);
    }
}
