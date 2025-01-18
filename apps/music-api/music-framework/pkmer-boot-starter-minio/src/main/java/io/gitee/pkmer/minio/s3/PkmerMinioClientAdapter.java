package io.gitee.pkmer.minio.s3;

import com.google.common.collect.Multimap;
import io.gitee.pkmer.minio.service.PartSummary;
import io.minio.CreateMultipartUploadResponse;
import io.minio.ListPartsResponse;
import io.minio.MinioAsyncClient;
import io.minio.ObjectWriteResponse;
import io.minio.messages.Part;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 封装minio 异步客户端，
 * 1. 本身是线程安全的
 * 2. 能够使用s3base封装的protect方法，如createMultipartUpload
 *
 */
@Slf4j
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


    /**
     * 获取已上传的分片列表
     * 通过递归方式获取所有分片信息，每次最多获取1000个分片
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param uploadId   分片上传ID
     * @return 已上传的分片信息列表
     */
    public List<PartSummary> listParts(String bucketName,
                                       String objectName,
                                       String uploadId,
                                       Integer maxParts) {
        try {
            ListPartsResponse response = super.listPartsAsync(
                    bucketName,
                    null,              // region
                    objectName,
                    maxParts,             // maxParts
                    null,  // 从哪个分片号开始获取
                    uploadId,
                    null,              // extraHeaders
                    null               // extraQueryParams
            ).get();

            // 转换并添加到结果列表
            List<PartSummary> parts = response.result().partList().stream()
                    .map(part -> PartSummary.builder()
                            .partNumber(part.partNumber())
                            .etag(part.etag())
                            .partSize(part.partSize())
                            .build())
                    .toList();

            log.info("获取到总分片数: {} - bucket: {}, object: {}", parts.size(), bucketName, objectName);
            return parts;

        } catch (Exception e) {
            log.error("获取分片列表失败 - bucket: {}, object: {}, uploadId: {}", bucketName, objectName, uploadId, e);
            throw new RuntimeException("获取分片列表失败: " + e.getMessage());
        }
    }


    /**
     * 完成分片上传
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param uploadId 上传ID
     * @param parts 已上传的分片信息
     * @return 合并后的文件ETag
     */
    public String completeMultipartUpload(String bucketName, String objectName, String uploadId, List<PartSummary> parts) {
        try {
            ObjectWriteResponse response = super.completeMultipartUploadAsync(
                    bucketName,
                    null,
                    objectName,
                    uploadId,
                    parts.stream()
                            .map(part -> new Part(part.getPartNumber(), part.getEtag()))
                            .toList().toArray(new Part[0]),
                    null,
                    null).get();

            return response.etag();
        } catch (Exception e) {
            throw new RuntimeException("Failed to complete multipart upload", e);
        }
    }

    /**
     * 中止分片上传
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @param uploadId 上传ID
     */
    public void abortMultipartUpload(String bucketName, String objectName, String uploadId) {
        try {
            super.abortMultipartUploadAsync(bucketName, null, objectName, uploadId, null, null).get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to abort multipart upload", e);
        }
    }


}
