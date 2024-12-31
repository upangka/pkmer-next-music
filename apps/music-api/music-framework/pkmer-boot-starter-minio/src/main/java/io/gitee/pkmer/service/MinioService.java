package io.gitee.pkmer.service;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio客户端封装
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@Slf4j
public class MinioService {
    private final MinioClient client;
    public MinioService(MinioClient client){
        this.client = client;
    }

    /**
     * 创建桶
     * @param bucketName 桶的名称
     */
    public void createBucket(String bucketName){
        try {
            if(!bucketExists(bucketName)){
                // 不存在创建这个bucket
                MakeBucketArgs args = MakeBucketArgs.builder().bucket(bucketName).build();
                client.makeBucket(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 桶是否存在
     * @param bucketName 桶名称
     * @return true 存在 | false 不存在
     */
    public boolean bucketExists(String bucketName) throws Exception {
        BucketExistsArgs args = BucketExistsArgs.builder().bucket(bucketName).build();
        return client.bucketExists(args);
    }

    /**
     * 上传文件
     * @param inputStream 文件输入流
     * @param objectName 存储对象名称
     * @param bucketName 桶的名称
     * @param contentType 内容类型
     */
    public void uploadFile(InputStream inputStream,String objectName, String bucketName,String contentType) throws Exception{
       log.info("上传的文件类型，{}",contentType);
        this.createBucket(bucketName);
        PutObjectArgs args = PutObjectArgs.builder().bucket(bucketName)
                .object(objectName).stream(
                        inputStream, -1, 10485760)
                .contentType(contentType)
                .build();
        client.putObject(args);
    }


    /**
     * 下载资源
     * @param bucketName 桶的名称
     * @param objectName 要下载的内容
     * @throws Exception
     */
    public InputStream downFile(String bucketName,String objectName) throws Exception {
        System.out.printf("%s,%s\n",bucketName,objectName);
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        return client.getObject(args);
    }
}
