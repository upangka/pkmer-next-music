package io.gitee.pkmer.minio.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUploadUrlReq {
    /**
     * 文件的MD5值，用于唯一标识文件内容
     */
    private String fileMd5;

    /**
     * 文件的完整名称，包括路径和扩展名
     */
    private String fullFileName;

    /**
     * 文件的大小，以字节为单位
     */
    private Long fileSize;
    /**
     * 是否为断点续传的标识
     * true:断点续传 false:非断点续传
     */
    private boolean isResumableUpload;
}

