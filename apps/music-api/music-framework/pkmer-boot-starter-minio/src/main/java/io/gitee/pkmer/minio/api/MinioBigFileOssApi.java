package io.gitee.pkmer.minio.api;


import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.minio.dto.MergeFileResult;
import io.gitee.pkmer.minio.service.FileInitView;
import io.gitee.pkmer.minio.service.ShardingView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "minio大文件操作")
public interface MinioBigFileOssApi extends MinioBasicOssApi {

    @Operation(summary = "初始化大文件上传")
    @PostMapping("/bigfile/init")
    Result<FileInitView> init(@Valid @RequestBody BigFileInitReq req);


    @Operation(summary = "分片上传小文件")
    @PostMapping("/bigfile/upload")
    default Object uploadShareFile(@RequestParam("uploadFile")
                                   MultipartFile uploadFile,
                                   @RequestParam("bucketName")
                                   String bucketName,
                                   @RequestParam(name = "fileName", required = false)
                                   String fileName) throws Exception {
        return uploadFile(uploadFile, bucketName, fileName);
    }


    @Operation(summary = "合并文件")
    @PostMapping("/bigfile/merge")
    Result<MergeFileResult> merge(
            @RequestParam("fileMd5") String fileMd5,
            @RequestBody List<String> partMd5List);


    @Operation(summary = "重试上传失败的分片")
    @Parameters({
            @Parameter(name = "fileMd5",description = "文件的md5",in = ParameterIn.QUERY),
            @Parameter(name = "partNumber",description = "分片编号",required = true,in = ParameterIn.QUERY),
    })
    @PostMapping("/retry")
    Result<String> retryUploadPart(@RequestParam("fileMd5") String fileMd5,
                                          @RequestParam("partNumber") int partNumber);

    @Operation(summary = "预上传分片信息")
    @PostMapping("/bigfile/sharding")
    Result<ShardingView> sharding(@Valid @RequestBody PreShardingReq req);

}
