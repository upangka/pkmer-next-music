package io.gitee.pkmer.minio.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "minio大文件操作")
public interface MinioBigFileOssApi extends MinioBasicOssApi {

    @Operation(summary = "初始化大文件上传")
    @PostMapping("/bigfile/init")
    void init(@RequestBody BigFileInitReq req);


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
    void merge(@RequestBody List<String> partMd5List);


}
