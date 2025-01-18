package io.gitee.pkmer.minio.repository.mybatis;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class FileMetadataInfoDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final FileMetadataInfo fileMetadataInfo = new FileMetadataInfo();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = fileMetadataInfo.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileKey = fileMetadataInfo.fileKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileMd5 = fileMetadataInfo.fileMd5;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileName = fileMetadataInfo.fileName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileMimeType = fileMetadataInfo.fileMimeType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileSuffix = fileMetadataInfo.fileSuffix;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> fileSize = fileMetadataInfo.fileSize;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> isPreview = fileMetadataInfo.isPreview;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> isPrivate = fileMetadataInfo.isPrivate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> bucket = fileMetadataInfo.bucket;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> bucketPath = fileMetadataInfo.bucketPath;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> uploadId = fileMetadataInfo.uploadId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> etag = fileMetadataInfo.etag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> isFinished = fileMetadataInfo.isFinished;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> isPart = fileMetadataInfo.isPart;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> partNumber = fileMetadataInfo.partNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> createTime = fileMetadataInfo.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createUser = fileMetadataInfo.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> updateTime = fileMetadataInfo.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updateUser = fileMetadataInfo.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class FileMetadataInfo extends AliasableSqlTable<FileMetadataInfo> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> fileKey = column("file_key", JDBCType.VARCHAR);

        public final SqlColumn<String> fileMd5 = column("file_md5", JDBCType.VARCHAR);

        public final SqlColumn<String> fileName = column("file_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fileMimeType = column("file_mime_type", JDBCType.VARCHAR);

        public final SqlColumn<String> fileSuffix = column("file_suffix", JDBCType.VARCHAR);

        public final SqlColumn<Long> fileSize = column("file_size", JDBCType.BIGINT);

        public final SqlColumn<Boolean> isPreview = column("is_preview", JDBCType.BIT);

        public final SqlColumn<Boolean> isPrivate = column("is_private", JDBCType.BIT);

        public final SqlColumn<String> bucket = column("bucket", JDBCType.VARCHAR);

        public final SqlColumn<String> bucketPath = column("bucket_path", JDBCType.VARCHAR);

        public final SqlColumn<String> uploadId = column("upload_id", JDBCType.VARCHAR);

        public final SqlColumn<String> etag = column("etag", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> isFinished = column("is_finished", JDBCType.BIT);

        public final SqlColumn<Boolean> isPart = column("is_part", JDBCType.BIT);

        public final SqlColumn<Integer> partNumber = column("part_number", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> createUser = column("create_user", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateUser = column("update_user", JDBCType.VARCHAR);

        public FileMetadataInfo() {
            super("file_metadata_info", FileMetadataInfo::new);
        }
    }
}