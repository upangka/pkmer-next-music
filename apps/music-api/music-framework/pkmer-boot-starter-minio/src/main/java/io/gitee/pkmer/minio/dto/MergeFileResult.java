package io.gitee.pkmer.minio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MergeFileResult {
    @Schema(description = "oss文件路径，用于数据库存储的信息")
    private String ossPath;
    @Schema(description = "oss文件预览路径")
    private String presignedObjectUrl;
}
