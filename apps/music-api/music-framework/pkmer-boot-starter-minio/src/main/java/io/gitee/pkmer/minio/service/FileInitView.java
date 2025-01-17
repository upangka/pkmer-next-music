package io.gitee.pkmer.minio.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "文件初始化结果")
public class FileInitView {

    @Schema(description = "文件元数据信息表的唯一标识ID")
    private Long id;

    @Schema(description = "文件在存储桶中的唯一键")
    private String fileKey;

    @Schema(description = "文件的MD5校验值")
    private String fileMd5;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "文件的MIME类型")
    private String fileMimeType;

    @Schema(description = "文件后缀名")
    private String fileSuffix;

    @Schema(description = "文件大小（字节）")
    private Long fileSize;

    @Schema(description = "文件所在的存储桶名称")
    private String bucket;

    @Schema(description = "文件在存储桶中的路径")
    private String bucketPath;

    @Schema(description = "切片上传任务id")
    private String uploadId;

    @Schema(description = "文件是否上传完成")
    private boolean isFinished;

    @Schema(description = "分片上传的分片数量")
    private Integer partNumber;

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
