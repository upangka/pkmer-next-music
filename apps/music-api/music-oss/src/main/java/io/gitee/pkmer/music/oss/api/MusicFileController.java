package io.gitee.pkmer.music.oss.api;

import io.gitee.pkmer.service.MinioService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@Slf4j
@RestController
@RequestMapping("/oss")
public class MusicFileController {

    @Setter(onMethod_ = @Autowired)
    private MinioClient minioClient;
    @Setter(onMethod_ = @Autowired)
    private MinioService minioService;

    @GetMapping("/listBuckets")
    public Object listBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.listBuckets()
                .stream()
                .map(Bucket::name)
                .toList();
    }

    @PostMapping("/upload")
    public Object uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile,
                             @RequestParam("bucketName") String bucketName,
                             @RequestParam(name = "fileName", required = false) String fileName) throws Exception {

        String objectName = fileName;
        if (objectName == null || !objectName.trim().isEmpty()) {
            objectName = uploadFile.getOriginalFilename();
        }

        minioService.uploadFile(
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
    @GetMapping(value = "/{bucketName}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable("bucketName") String bucketName,
            @RequestParam("filename") String fileName) {
        // 在springmvc 中已经解码了： Summer Dance & Ya! 现场版 李贞贤.mp3
        log.info("准备下载{}", fileName);
        try (InputStream inputStream = minioService.downFile(bucketName, fileName)) {
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
     * @param fileName
     * @return
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
}



