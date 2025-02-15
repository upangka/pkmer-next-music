package io.gitee.pkmer.minio.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 存储对象的名称和oss路径
 */
@Data
@Builder
public class ObjectNameAndOssPath {
    /**
     * 存储对象名称 如 2025/02/黄昏DJ.flac
     * 用于生成minio预链接
     */
    private String objectName;
    /**
     * oss路径 /bucket/objectName
     * 用于mysql存储
     */
    private String ossPath;
}
