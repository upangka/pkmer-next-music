package io.gitee.pkmer.music.oss.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/oss")
public interface MusicOssApi {

    @Operation(summary = "获取所有存储桶")
    @GetMapping("/listBuckets")
    Object listBuckets() throws Exception;


    @Operation(summary = "上传文件")
    @Parameters({
            @Parameter(name = "uploadFile",description = "文件",in = ParameterIn.QUERY),
            @Parameter(name = "bucketName",description = "上传到桶的名称",required = true,in = ParameterIn.QUERY),
            @Parameter(name = "fileName",description = "文件名称",required = false,in=ParameterIn.QUERY)
    })
    @PostMapping("/upload")
    Object uploadFile(@RequestParam("uploadFile")
                      MultipartFile uploadFile,
                      @RequestParam("bucketName")
                      String bucketName,
                      @RequestParam(name = "fileName", required = false)
                      String fileName) throws Exception;


    /**
     * 前端通过encodeURIComponent("Summer Dance & Ya!  现场版   李贞贤.mp3")
     * Summer%20Dance%20%26%20Ya!%20%20%E7%8E%B0%E5%9C%BA%E7%89%88%20%20%20%E6%9D%8E%E8%B4%9E%E8%B4%A4.mp3
     * 在springmvc已经能够拿到解码的内容 Summer Dance & Ya! 现场版 李贞贤.mp3
     * 并不需要自己解码
     *
     * @param bucketName 桶名称
     * @param fileName   Summer Dance & Ya! 现场版 李贞贤.mp3
     */
    @Operation(summary = "下载文件")
    @Parameters({
            @Parameter(name = "bucketName",description = "桶名称",required = true,in = ParameterIn.PATH),
            @Parameter(name = "filename",description = "文件名称",required = true,in = ParameterIn.QUERY)
    })
    @GetMapping(value = "/{bucketName}/download")
    ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable("bucketName") String bucketName,
            @RequestParam("filename") String fileName);

    @Operation(summary = "获取指定桶下的文件列表")
    @Parameters({
            @Parameter(name = "bucket",description = "桶名称",required = true,in = ParameterIn.PATH)
    })
    @GetMapping("/{bucket}/listfiles")
    Object listFilesOfBucketName(@PathVariable("bucket") String bucket) throws Exception;



    @Operation(summary = "获取文件预授权外链")
    @Parameters({
            @Parameter(name = "bucketName",description = "桶名称",required = true,in = ParameterIn.QUERY),
            @Parameter(name = "objectName",description = "文件名称",required = true,in = ParameterIn.QUERY),
            @Parameter(name = "expires",description = "过期时间",required = false,in = ParameterIn.QUERY)
    })
    @GetMapping("/getPresignedObjectUrl")
    String getPresignedObjectUrl(
            @RequestParam("bucketName")
            String bucketName,
            @RequestParam("objectName")
            String objectName,
            @RequestParam(value = "expires",required = false)
            Integer expires) throws Exception;
}
