package io.gitee.pkmer.minio.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ShardingView {
    @Schema(description = "分片总数")
    private Integer totalParts;
    @Schema(description = "文件大小")
    private Long fileSize;
    @Schema(description = "分片大小")
    private Long chunkSize;
    @Schema(description = "分片列表")
    private List<Part> parts;

    @Data
    @Builder
    public static class Part{
        @Schema(description = "分片序号")
        private Integer partNumber;
        @Schema(description = "分片开始位置")
        private Long start;
        @Schema(description = "分片结束位置")
        private Long end;
    }
}
