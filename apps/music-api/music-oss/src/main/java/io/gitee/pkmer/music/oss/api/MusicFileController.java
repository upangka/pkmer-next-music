package io.gitee.pkmer.music.oss.api;

import io.gitee.pkmer.service.MinioService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
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
        if(objectName == null || !objectName.trim().isEmpty()){
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
}
