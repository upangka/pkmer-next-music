package io.gitee.pkmer.minio.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.minio.s3.PkmerMinioClientAdapter;
import io.gitee.pkmer.minio.s3.S3BaseSupport;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Minio客户端封装
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@Slf4j
public class MinioAdapter{
    private final PkmerMinioClientAdapter client;
    private final int expires;
    public MinioAdapter(PkmerMinioClientAdapter client, PkmerMinioProps props){
        this.client = client;
        this.expires = props.getExpiresOfMinutes();
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
        return client.bucketExists(args).get();
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
        return client.getObject(args).get();
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
     */
    public String getPresignedObjectUrl(String bucketName,String objectName) throws Exception {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(expires, TimeUnit.MINUTES)
                .build();

        return client.getPresignedObjectUrl(args);
    }


    /**
     * 初始化一个分段上传任务
     *
     * 此方法用于创建一个分段上传任务，并返回上传ID该ID用于后续的上传操作
     * 使用异步方式调用AWS S3客户端发起创建分段上传请求，以获取上传ID
     *
     * @return String 返回上传ID，用于后续的上传操作
     * @throws RuntimeException 如果在获取上传ID过程中发生错误，抛出运行时异常
     */
    public String createMultipartUpload(String bucketName,String objectName,String mimeType){

        HashMultimap<String, String> headers = HashMultimap.<String, String>create();
        if(mimeType != null && !mimeType.isBlank()){
            headers.put("Content-Type", mimeType);
        }

        CompletableFuture<CreateMultipartUploadResponse> multipartUploadAsync = client.createMultipartUploadAsync(
                bucketName,
                null,
                objectName,
                headers,
                null);
        try {
            CreateMultipartUploadResponse response = multipartUploadAsync.get();
            return response.result().uploadId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建上传URL
     * 该方法用于生成一个带有预签名的URL，用于上传特定部分的文件到指定的存储桶
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称，即存储桶中的文件名
     * @param uploadId 多部分上传的唯一标识符
     * @param partNumber 文件部分编号，用于标识文件的特定部分
     * @return 返回生成的上传URL
     *
     * 使用了ImmutableMap来构建查询参数，并使用GetPresignedObjectUrlArgs来配置请求的详细信息
     * 通过MinIO客户端的getPresignedObjectUrl方法获取预签名的上传URL
     * 如果在获取URL过程中遇到异常，将抛出运行时异常
     */
    public String createUploadUrl(String bucketName,
                                  String objectName,
                                  String uploadId,
                                  String partNumber){

        ImmutableMap<String, String> queryParams = ImmutableMap.of(
                S3BaseSupport.UPLOAD_ID, uploadId,
                S3BaseSupport.PART_NUMBER, partNumber);

        // https://min.io/docs/minio/linux/developers/java/API.html#getPresignedObjectUrl
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(bucketName)
                .object(objectName)
                .expiry(expires, TimeUnit.MINUTES)
                .extraQueryParams(queryParams)
                .build();
        try {
            return client.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取已上传的分片列表
     * 通过递归方式获取所有分片信息，每次最多获取1000个分片
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param uploadId   分片上传ID
     * @return 已上传的分片信息列表
     */
    public List<PartSummary> listParts(String bucketName, String objectName, String uploadId) {
        return client.listParts(bucketName, objectName, uploadId);
    }

}
