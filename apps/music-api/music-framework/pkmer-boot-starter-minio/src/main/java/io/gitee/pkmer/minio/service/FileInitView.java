package io.gitee.pkmer.minio.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "文件初始化结果")
public class FileInitView {

    @Schema(description = "文件分片列表")
    private List<Part> parts;

    @Data
    @Builder
    @Schema(description = "文件分片")
    public static class Part{
        @Schema(description = "上传ID")
        private String uploadId;
        @Schema(description = "上传地址")
        private String uploadUrl;
        @Schema(description = "分片序号")
        private Integer partNumber;
        @Schema(description = "分片起始位置")
        private Long shardingStart;
        @Schema(description = "分片结束位置")
        private Long shardingEnd;
    }
}
