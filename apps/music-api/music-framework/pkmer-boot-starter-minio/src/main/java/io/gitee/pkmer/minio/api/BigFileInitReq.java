package io.gitee.pkmer.minio.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BigFileInitReq {
    @Schema(description = "文件md5")
    @NotBlank
    private String fileMd5;
    @Schema(description = "文件名称")
    @NotBlank
    private String fullFileName;
    @Schema(description = "文件大小")
    @NotNull
    @Min(value = 1, message = "文件大小不能小于1")
    private Integer fileSize;
}
