package io.gitee.pkmer.minio.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MimeTypeUtil {

    // 静态Map存储文件扩展名和MIME类型的映射关系
    private static final Map<String, String> MIME_TYPE_MAP = Map.of(
            ".css", "text/css",
            ".js", "application/x-javascript",
            ".rar", "application/x-rar-compressed",
            ".7z", "application/x-7z-compressed",
            ".wgt", "application/widget",
            ".webp", "image/webp"
    );

    /**
     * 根据文件路径获取文件的MIME类型
     *
     * @param filePath 文件路径
     * @return 文件的MIME类型，如果无法确定则返回null
     */
    public static String getMimeType(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            return null;
        }

        // 转换为小写以实现不区分大小写的匹配
        String filePathLower = filePath.toLowerCase();

        // 获取文件扩展名对应的MIME类型
        for (Map.Entry<String, String> entry : MIME_TYPE_MAP.entrySet()) {
            if (filePathLower.endsWith(entry.getKey())) {
                return entry.getValue();
            }
        }

        // 如果Map中找不到MIME类型，则使用系统的默认探测方式
        String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
        if (contentType == null) {
            // 如果文件名无法确定MIME类型，尝试通过文件路径进一步获取
            try {
                contentType = Files.probeContentType(Path.of(filePath));
            } catch (IOException e) {
                log.error("获取文件MIME类型失败", e);
                throw new RuntimeException(e);
            }
        }

        return contentType;
    }
}
