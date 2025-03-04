package io.gitee.pkmer.music.oss.api;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.minio.api.BigFileInitReq;
import io.gitee.pkmer.minio.api.MinioBigFileOssApi;
import io.gitee.pkmer.minio.api.PreShardingReq;
import io.gitee.pkmer.minio.dto.MergeFileResult;
import io.gitee.pkmer.minio.s3.PkmerMinioClientAdapter;
import io.gitee.pkmer.minio.service.FileInitView;
import io.gitee.pkmer.minio.service.MinioAdapter;
import io.gitee.pkmer.minio.service.MinioEngineService;
import io.gitee.pkmer.minio.service.ShardingView;
import io.gitee.pkmer.security.context.AppContextHolder;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MusicFileController extends BaseController implements MinioBigFileOssApi {

    private final PkmerMinioClientAdapter pkmerMinioClient;
    private final MinioAdapter minioAdapter;
    private final MinioEngineService minioEngineService;

    @Override
    public Object listBuckets() throws Exception {
        return pkmerMinioClient.listBuckets().get()
                .stream()
                .map(Bucket::name)
                .toList();
    }

    @Override
    public Object uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile,
                             @RequestParam("bucketName") String bucketName,
                             @RequestParam(name = "fileName", required = false) String fileName) throws Exception {

        String objectName = fileName;
        if (objectName == null || !objectName.trim().isEmpty()) {
            objectName = uploadFile.getOriginalFilename();
        }

        minioAdapter.uploadFile(
                uploadFile.getInputStream(),
                objectName,
                bucketName,
                uploadFile.getContentType()
        );

        return "success";
    }


    /**
     * 前端通过encodeURIComponent("Summer Dance & Ya!  现场版   李贞贤.mp3")
     * Summer%20Dance%20%26%20Ya!%20%20%E7%8E%B0%E5%9C%BA%E7%89%88%20%20%20%E6%9D%8E%E8%B4%9E%E8%B4%A4.mp3
     * 在springmvc已经能够拿到解码的内容 Summer Dance & Ya! 现场版 李贞贤.mp3
     * 并不需要自己解码
     *
     * @param bucketName 桶名称
     * @param fileName   Summer Dance & Ya! 现场版 李贞贤.mp3
     */
    @Override
    public ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable("bucketName") String bucketName,
            @RequestParam("filename") String fileName) {
        // 在springmvc 中已经解码了： Summer Dance & Ya! 现场版 李贞贤.mp3
        log.info("准备下载{}", fileName);
        try (InputStream inputStream = minioAdapter.downFile(bucketName, fileName)) {
            byte[] content = inputStream.readAllBytes();
            ByteArrayResource resource = new ByteArrayResource(content);
            return ResponseEntity.ok()
                    .headers(buildHttpHeaders(fileName))
                    .body(resource);
        } catch (Exception e) {
            log.error("报错{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    /**
     * 构造下载文件的请求头
     *
     * @param fileName 文件名
     */
    private HttpHeaders buildHttpHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        // java当中会把空格处理成+，在这里我们替换成" "
        String encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                .replace("+", "%20");
        // 避免文件名中带空格或特殊字符时，浏览器解析错误,这里用双引号进行包裹
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodeFileName + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return headers;
    }

    /**
     * 获取一个桶下的所有文件
     */
    @Override
    public Object listFilesOfBucketName(@PathVariable("bucket") String bucket) throws Exception {
       return  minioAdapter.listFiles(bucket);
    }

    @Override
    public String getPresignedObjectUrl(
            @RequestParam("bucketName")
            String bucketName,
            @RequestParam("objectName")
            String objectName,
            @RequestParam(value = "expires",required = false)
            Integer expires) throws Exception {

        if(expires == null || expires <= 0){
            log.info("设置默认过期时间1days");
            expires = 24;
        }

        return minioAdapter.getPresignedObjectUrl(bucketName,objectName);
    }

    @Override
    public Result<FileInitView> init(BigFileInitReq req) {
        FileInitView view =  minioEngineService.init(req.getFileMd5(),req.getFullFileName(),req.getFileSize());
        return success(view);
    }


    public Result<MergeFileResult> merge(@RequestParam("fileMd5") String fileMd5,
                                @RequestBody List<String> partMd5List) {
        try {
            MergeFileResult mergeFileResult = minioEngineService.mergeFile(
                    fileMd5,
                    partMd5List,
                    getUserId());
            return success(mergeFileResult);
        } catch (Exception e) {
            log.error("Failed to merge file parts", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 重试上传失败的分片
     */
    @PostMapping("/retry")
    public Result<String> retryUploadPart(@RequestParam("fileMd5") String fileMd5,
                                          @RequestParam("partNumber") int partNumber) {
        try {
            String uploadUrl = minioEngineService.retryUploadPart(
                    fileMd5,
                    partNumber,
                    getUserId());
            return success(uploadUrl);
        } catch (Exception e) {
            log.error("Failed to generate retry upload url", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result<ShardingView> sharding(PreShardingReq req) {
        ShardingView view = minioEngineService.sharding(req.getFileSize());
        return success(view);
    }


    private String getUserId(){
        return AppContextHolder.userContextHolder.getUser().getId().toString();
    }
}



