package io.gitee.pkmer.minio.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件分片信息摘要类
 * 用于存储已上传分片的基本信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartSummary {
    /**
     * 分片号，从1开始
     */
    private int partNumber;

    /**
     * 分片的ETag值，用于验证分片完整性
     */
    private String etag;

    /**
     * 分片大小（字节）
     */
    private long partSize;
}