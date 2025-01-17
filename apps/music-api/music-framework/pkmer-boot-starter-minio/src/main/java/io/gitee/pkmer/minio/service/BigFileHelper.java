
package io.gitee.pkmer.minio.service;

import io.gitee.pkmer.minio.props.PkmerMinioProps;

public class BigFileHelper {

    private final long CHUNK_SIZE;

    public BigFileHelper(PkmerMinioProps props) {
        String defaultChunkSize = props.getDefaultChunkSize();
        this.CHUNK_SIZE = convertToBytes(defaultChunkSize);
    }

    /**
     * 计算文件分片的数量
     *
     * @param fileSize 文件的大小，以字节为单位
     * @return 分片的数量
     */
    public int computeChunks(long fileSize) {
        return (int) Math.ceil((double) fileSize / CHUNK_SIZE);
    }

    /**
     * 将文件大小转换为字节数
     * @param fileSize 文件大小
     * @return
     */
    public static long convertToBytes(String fileSize) {
        if (fileSize == null || fileSize.isEmpty()) {
            throw new IllegalArgumentException("文件大小不能为空");
        }

        fileSize = fileSize.trim().toUpperCase(); // 统一格式处理

        // 检测单位并转换
        if (fileSize.endsWith("KB")) {
            return parseSize(fileSize, "KB", 1024);
        } else if (fileSize.endsWith("MB")) {
            return parseSize(fileSize, "MB", 1024 * 1024);
        } else if (fileSize.endsWith("GB")) {
            return parseSize(fileSize, "GB", 1024L * 1024 * 1024);
        } else {
            throw new IllegalArgumentException("不支持的文件大小单位：" + fileSize);
        }
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
            String numberPart = fileSize.substring(0, fileSize.length() - unit.length()).trim();
            return Long.parseLong(numberPart) * multiplier;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("文件大小格式错误：" + fileSize, e);
        }
    }


    /**
     * 提取文件名的后缀
     * @param fileName 文件名
     * @return 后缀（不包括.），如果没有后缀则返回空字符串
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            // 没有点或点在末尾（无后缀）
            return "";
        }

        return fileName.substring(lastDotIndex + 1);
    }
}


