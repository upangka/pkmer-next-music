package io.gitee.pkmer.minio.service;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Minio客户端封装
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@Slf4j
public class MinioAdapter {
    private final MinioClient client;
    public MinioAdapter(MinioClient client){
        this.client = client;
    }

    /**
     * <a href="https://min.io/docs/minio/linux/developers/java/API.html#makeBucket">创建桶</a>
     *
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


    /**
     * 获取一个桶下的所有文件
     * @param bucketName 桶名称
     * @throws Exception
     */
    public List<String> listFiles(String bucketName) throws Exception {
        Iterable<Result<Item>> results = client.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).recursive(true).build());
        List<String> files = new ArrayList<>();

        for(Result<Item> r: results){
            Item item = r.get();
            if(item.isDir()) continue;
            files.add(item.objectName());
        }
        return files;
    }

    /**
     * 获取一个预签名的下载链接
     * @param bucketName 桶的名臣
     * @param objectName 对象名称
     * @param expires 单位为小时
     */
    public String getPresignedObjectUrl(String bucketName,String objectName,Integer expires) throws Exception {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(1, TimeUnit.HOURS)
                .build();

        return client.getPresignedObjectUrl(args);
    }
}
