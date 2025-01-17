package io.gitee.pkmer.minio.utils;

public class FileUtil {
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
