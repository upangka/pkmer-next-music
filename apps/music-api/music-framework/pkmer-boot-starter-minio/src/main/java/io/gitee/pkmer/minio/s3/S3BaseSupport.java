package io.gitee.pkmer.minio.s3;

public interface S3BaseSupport {
    /**
     * MinIO中上传编号名称
     */
    String UPLOAD_ID = "uploadId";
    /**
     * 分片上传块号名称
     */
    String PART_NUMBER = "partNumber";
}
