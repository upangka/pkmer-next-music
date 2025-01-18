package io.gitee.pkmer.minio.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PreShardingReq {
    @Schema(description = "文件md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long fileSize;
}
