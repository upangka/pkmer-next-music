package io.gitee.pkmer.minio.repository.mybatis;

import io.gitee.pkmer.mybatis.annotations.SnowflakeId;
import jakarta.annotation.Generated;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * generated by MyBatis Generator.
 * file_metadata_info(文件元数据信息表) 
 * 
 * @author 胖卡
 * @version 1.0.0
 * @date 2025/01/18
 */
@Data
public class FileMetadataInfo {

    @SnowflakeId
    private Long id;


    private String fileKey;


    private String fileMd5;


    private String fileName;


    private String fileMimeType;


    private String fileSuffix;


    private Long fileSize;


    private Boolean isPreview;


    private Boolean isPrivate;


    private String bucket;


    private String bucketPath;


    private String uploadId;


    private String etag;


    private Boolean isFinished;


    private Boolean isPart;


    private Integer partNumber;


    private LocalDateTime createTime;


    private String createUser;


    private LocalDateTime updateTime;


    private String updateUser;

}