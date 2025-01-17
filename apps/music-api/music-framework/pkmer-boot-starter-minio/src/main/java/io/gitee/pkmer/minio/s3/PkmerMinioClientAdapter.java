package io.gitee.pkmer.minio.s3;

import com.google.common.collect.Multimap;
import io.minio.CreateMultipartUploadResponse;
import io.minio.MinioAsyncClient;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.XmlParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 封装minio 异步客户端，
 * 1. 本身是线程安全的
 * 2. 能够使用s3base封装的protect方法，如createMultipartUpload
 *
 */
public class PkmerMinioClientAdapter extends MinioAsyncClient {
    public PkmerMinioClientAdapter(MinioAsyncClient client) {
        super(client);
    }

    /**
     * 异步启动一个分块上传操作。
     * 此方法用于在与 S3 兼容的存储服务上启动分块上传任务。分块上传允许将一个大文件拆分成多个部分进行上传，
     * 适用于上传大文件或在不稳定的网络条件下上传。该方法是异步的，返回一个 CompletableFuture 对象，您可以使用该对象
     * 来处理操作的结果或异常。
     *
     * @param bucketName 存储桶的名称。
     * @param region 存储桶所在的区域名称。
     * @param objectName 存储桶中的对象名称。
     * @param headers 请求头。
     * @param extraQueryParams 请求的额外查询参数（可选）。
     */
    public CompletableFuture<CreateMultipartUploadResponse> createMultipartUploadAsync(
            String bucketName,
            String region,
            String objectName,
            Multimap<String, String> headers,
            Multimap<String, String> extraQueryParams
    ) {
        try{
            return super.createMultipartUploadAsync(bucketName, region, objectName, headers, extraQueryParams);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
