package io.gitee.pkmer.minio.repository.mybatis;

import static io.gitee.pkmer.minio.repository.mybatis.FileMetadataInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.gitee.pkmer.minio.repository.mybatis.FileMetadataInfo;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface FileMetadataInfoMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<FileMetadataInfo>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, fileKey, fileMd5, fileName, fileMimeType, fileSuffix, fileSize, isPreview, isPrivate, bucket, bucketPath, uploadId, etag, isFinished, isPart, partNumber, createTime, createUser, updateTime, updateUser);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="FileMetadataInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="file_key", property="fileKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_md5", property="fileMd5", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_mime_type", property="fileMimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_suffix", property="fileSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_size", property="fileSize", jdbcType=JdbcType.BIGINT),
        @Result(column="is_preview", property="isPreview", jdbcType=JdbcType.BIT),
        @Result(column="is_private", property="isPrivate", jdbcType=JdbcType.BIT),
        @Result(column="bucket", property="bucket", jdbcType=JdbcType.VARCHAR),
        @Result(column="bucket_path", property="bucketPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_id", property="uploadId", jdbcType=JdbcType.VARCHAR),
        @Result(column="etag", property="etag", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_finished", property="isFinished", jdbcType=JdbcType.BIT),
        @Result(column="is_part", property="isPart", jdbcType=JdbcType.BIT),
        @Result(column="part_number", property="partNumber", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.VARCHAR)
    })
    List<FileMetadataInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("FileMetadataInfoResult")
    Optional<FileMetadataInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, fileMetadataInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, fileMetadataInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(FileMetadataInfo row) {
        return MyBatis3Utils.insert(this::insert, row, fileMetadataInfo, c ->
            c.map(id).toProperty("id")
            .map(fileKey).toProperty("fileKey")
            .map(fileMd5).toProperty("fileMd5")
            .map(fileName).toProperty("fileName")
            .map(fileMimeType).toProperty("fileMimeType")
            .map(fileSuffix).toProperty("fileSuffix")
            .map(fileSize).toProperty("fileSize")
            .map(isPreview).toProperty("isPreview")
            .map(isPrivate).toProperty("isPrivate")
            .map(bucket).toProperty("bucket")
            .map(bucketPath).toProperty("bucketPath")
            .map(uploadId).toProperty("uploadId")
            .map(etag).toProperty("etag")
            .map(isFinished).toProperty("isFinished")
            .map(isPart).toProperty("isPart")
            .map(partNumber).toProperty("partNumber")
            .map(createTime).toProperty("createTime")
            .map(createUser).toProperty("createUser")
            .map(updateTime).toProperty("updateTime")
            .map(updateUser).toProperty("updateUser")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<FileMetadataInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, fileMetadataInfo, c ->
            c.map(id).toProperty("id")
            .map(fileKey).toProperty("fileKey")
            .map(fileMd5).toProperty("fileMd5")
            .map(fileName).toProperty("fileName")
            .map(fileMimeType).toProperty("fileMimeType")
            .map(fileSuffix).toProperty("fileSuffix")
            .map(fileSize).toProperty("fileSize")
            .map(isPreview).toProperty("isPreview")
            .map(isPrivate).toProperty("isPrivate")
            .map(bucket).toProperty("bucket")
            .map(bucketPath).toProperty("bucketPath")
            .map(uploadId).toProperty("uploadId")
            .map(etag).toProperty("etag")
            .map(isFinished).toProperty("isFinished")
            .map(isPart).toProperty("isPart")
            .map(partNumber).toProperty("partNumber")
            .map(createTime).toProperty("createTime")
            .map(createUser).toProperty("createUser")
            .map(updateTime).toProperty("updateTime")
            .map(updateUser).toProperty("updateUser")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(FileMetadataInfo row) {
        return MyBatis3Utils.insert(this::insert, row, fileMetadataInfo, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(fileKey).toPropertyWhenPresent("fileKey", row::getFileKey)
            .map(fileMd5).toPropertyWhenPresent("fileMd5", row::getFileMd5)
            .map(fileName).toPropertyWhenPresent("fileName", row::getFileName)
            .map(fileMimeType).toPropertyWhenPresent("fileMimeType", row::getFileMimeType)
            .map(fileSuffix).toPropertyWhenPresent("fileSuffix", row::getFileSuffix)
            .map(fileSize).toPropertyWhenPresent("fileSize", row::getFileSize)
            .map(isPreview).toPropertyWhenPresent("isPreview", row::getIsPreview)
            .map(isPrivate).toPropertyWhenPresent("isPrivate", row::getIsPrivate)
            .map(bucket).toPropertyWhenPresent("bucket", row::getBucket)
            .map(bucketPath).toPropertyWhenPresent("bucketPath", row::getBucketPath)
            .map(uploadId).toPropertyWhenPresent("uploadId", row::getUploadId)
            .map(etag).toPropertyWhenPresent("etag", row::getEtag)
            .map(isFinished).toPropertyWhenPresent("isFinished", row::getIsFinished)
            .map(isPart).toPropertyWhenPresent("isPart", row::getIsPart)
            .map(partNumber).toPropertyWhenPresent("partNumber", row::getPartNumber)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(createUser).toPropertyWhenPresent("createUser", row::getCreateUser)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(updateUser).toPropertyWhenPresent("updateUser", row::getUpdateUser)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<FileMetadataInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, fileMetadataInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<FileMetadataInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, fileMetadataInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<FileMetadataInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, fileMetadataInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<FileMetadataInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, fileMetadataInfo, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(FileMetadataInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(fileKey).equalTo(row::getFileKey)
                .set(fileMd5).equalTo(row::getFileMd5)
                .set(fileName).equalTo(row::getFileName)
                .set(fileMimeType).equalTo(row::getFileMimeType)
                .set(fileSuffix).equalTo(row::getFileSuffix)
                .set(fileSize).equalTo(row::getFileSize)
                .set(isPreview).equalTo(row::getIsPreview)
                .set(isPrivate).equalTo(row::getIsPrivate)
                .set(bucket).equalTo(row::getBucket)
                .set(bucketPath).equalTo(row::getBucketPath)
                .set(uploadId).equalTo(row::getUploadId)
                .set(etag).equalTo(row::getEtag)
                .set(isFinished).equalTo(row::getIsFinished)
                .set(isPart).equalTo(row::getIsPart)
                .set(partNumber).equalTo(row::getPartNumber)
                .set(createTime).equalTo(row::getCreateTime)
                .set(createUser).equalTo(row::getCreateUser)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(updateUser).equalTo(row::getUpdateUser);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(FileMetadataInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(fileKey).equalToWhenPresent(row::getFileKey)
                .set(fileMd5).equalToWhenPresent(row::getFileMd5)
                .set(fileName).equalToWhenPresent(row::getFileName)
                .set(fileMimeType).equalToWhenPresent(row::getFileMimeType)
                .set(fileSuffix).equalToWhenPresent(row::getFileSuffix)
                .set(fileSize).equalToWhenPresent(row::getFileSize)
                .set(isPreview).equalToWhenPresent(row::getIsPreview)
                .set(isPrivate).equalToWhenPresent(row::getIsPrivate)
                .set(bucket).equalToWhenPresent(row::getBucket)
                .set(bucketPath).equalToWhenPresent(row::getBucketPath)
                .set(uploadId).equalToWhenPresent(row::getUploadId)
                .set(etag).equalToWhenPresent(row::getEtag)
                .set(isFinished).equalToWhenPresent(row::getIsFinished)
                .set(isPart).equalToWhenPresent(row::getIsPart)
                .set(partNumber).equalToWhenPresent(row::getPartNumber)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(createUser).equalToWhenPresent(row::getCreateUser)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(updateUser).equalToWhenPresent(row::getUpdateUser);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(FileMetadataInfo row) {
        return update(c ->
            c.set(fileKey).equalTo(row::getFileKey)
            .set(fileMd5).equalTo(row::getFileMd5)
            .set(fileName).equalTo(row::getFileName)
            .set(fileMimeType).equalTo(row::getFileMimeType)
            .set(fileSuffix).equalTo(row::getFileSuffix)
            .set(fileSize).equalTo(row::getFileSize)
            .set(isPreview).equalTo(row::getIsPreview)
            .set(isPrivate).equalTo(row::getIsPrivate)
            .set(bucket).equalTo(row::getBucket)
            .set(bucketPath).equalTo(row::getBucketPath)
            .set(uploadId).equalTo(row::getUploadId)
            .set(etag).equalTo(row::getEtag)
            .set(isFinished).equalTo(row::getIsFinished)
            .set(isPart).equalTo(row::getIsPart)
            .set(partNumber).equalTo(row::getPartNumber)
            .set(createTime).equalTo(row::getCreateTime)
            .set(createUser).equalTo(row::getCreateUser)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(updateUser).equalTo(row::getUpdateUser)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(FileMetadataInfo row) {
        return update(c ->
            c.set(fileKey).equalToWhenPresent(row::getFileKey)
            .set(fileMd5).equalToWhenPresent(row::getFileMd5)
            .set(fileName).equalToWhenPresent(row::getFileName)
            .set(fileMimeType).equalToWhenPresent(row::getFileMimeType)
            .set(fileSuffix).equalToWhenPresent(row::getFileSuffix)
            .set(fileSize).equalToWhenPresent(row::getFileSize)
            .set(isPreview).equalToWhenPresent(row::getIsPreview)
            .set(isPrivate).equalToWhenPresent(row::getIsPrivate)
            .set(bucket).equalToWhenPresent(row::getBucket)
            .set(bucketPath).equalToWhenPresent(row::getBucketPath)
            .set(uploadId).equalToWhenPresent(row::getUploadId)
            .set(etag).equalToWhenPresent(row::getEtag)
            .set(isFinished).equalToWhenPresent(row::getIsFinished)
            .set(isPart).equalToWhenPresent(row::getIsPart)
            .set(partNumber).equalToWhenPresent(row::getPartNumber)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(createUser).equalToWhenPresent(row::getCreateUser)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(updateUser).equalToWhenPresent(row::getUpdateUser)
            .where(id, isEqualTo(row::getId))
        );
    }
}