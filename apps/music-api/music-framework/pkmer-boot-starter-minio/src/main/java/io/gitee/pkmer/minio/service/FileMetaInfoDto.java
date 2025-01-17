package io.gitee.pkmer.minio.service;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FileMetaInfoDto {
    /**
     * 文件元数据信息表的唯一标识ID
     */
    private Long id;

    /**
     * 文件在存储桶中的唯一键
     */
    private String fileKey;

    /**
     * 文件的MD5校验值
     */
    private String fileMd5;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件的MIME类型
     */
    private String fileMimeType;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件是否可预览
     */
    private Boolean isPreview;

    /**
     * 文件是否私有
     */
    private Boolean isPrivate;

    /**
     * 文件所在的存储桶名称
     */
    private String bucket;

    /**
     * 文件在存储桶中的路径
     */
    private String bucketPath;

    /**
     * 文件上传的唯一标识ID
     */
    private String uploadId;

    /**
     * 文件是否上传完成
     */
    private Boolean isFinished;

    /**
     * 文件是否为分片上传
     */
    private Boolean isPart;

    /**
     * 分片上传的分片编号
     */
    private Integer partNumber;

    /**
     * 文件创建时间
     */
    private LocalDateTime createTime;

    /**
     * 文件创建者
     */
    private String createUser;

    /**
     * 文件更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 文件更新者
     */
    private String updateUser;
}
