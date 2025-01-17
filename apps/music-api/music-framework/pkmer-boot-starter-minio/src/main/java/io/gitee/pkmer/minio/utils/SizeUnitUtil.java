package io.gitee.pkmer.minio.utils;

import java.util.HashMap;
import java.util.Map;

public class SizeUnitUtil {
    // 定义单位与倍数的映射关系
    private static final Map<String, Long> SIZE_UNITS = Map.of(
            "KB", 1024L,
            "MB", 1024L * 1024,
            "GB", 1024L * 1024 * 1024
    );


    /**
     * 将文件大小转换为字节数
     * @param fileSize 文件大小
     */
    public static  long convertToBytes(String fileSize) {
        if (fileSize == null || fileSize.isEmpty()) {
            throw new IllegalArgumentException("文件大小不能为空");
        }

        fileSize = fileSize.trim().toUpperCase(); // 统一格式处理

        // 检测单位并转换
        // 遍历单位映射，匹配并进行转换
        for (Map.Entry<String, Long> entry : SIZE_UNITS.entrySet()) {
            if (fileSize.endsWith(entry.getKey())) {
                return parseSize(fileSize, entry.getKey(), entry.getValue());
            }
        }

        throw new IllegalArgumentException("不支持的文件大小单位：" + fileSize);
    }

    /**
     * 解析文件大小字符串为长整型值
     *
     * @param fileSize 文件大小字符串，包含数字和单位，例如 "100KB"
     * @param unit 文件大小的单位，例如 "KB"、"MB"等
     * @param multiplier 单位对应的倍数，用于将文件大小转换为字节
     * @return 文件大小的长整型值，以字节为单位
     * @throws IllegalArgumentException 如果文件大小的数字部分不是有效的长整型数字
     */

    private static long parseSize(String fileSize, String unit, long multiplier) {
        try {
            // 处理数字部分
            String numberPart = fileSize.substring(0, fileSize.length() - unit.length()).trim();
            return Long.parseLong(numberPart) * multiplier;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("文件大小格式错误：" + fileSize, e);
        }
    }
}
