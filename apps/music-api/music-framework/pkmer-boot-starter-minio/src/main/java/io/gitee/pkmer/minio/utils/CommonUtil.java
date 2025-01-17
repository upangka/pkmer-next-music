package io.gitee.pkmer.minio.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
    // 将 DateTimeFormatter 定义为 static 常量
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM");

    /**
     * 根据当前日期生成文件路径
     *
     * 该方法旨在动态生成与当前日期相关的文件路径这在需要按日期组织文件或数据时特别有用
     * 它通过获取当前时间并使用预定义的格式器（FORMATTER）进行格式化来实现此功能
     * 由于文件路径中的日期格式可能包含与操作系统文件路径分隔符不兼容的字符（如‘/’），
     * 该方法还会将这些字符替换为操作系统兼容的分隔符
     *
     * @return 当前日期格式化后的字符串，用作文件路径的一部分
     */
    public static String getPathByDate() {
        // 使用 FORMATTER 格式化当前时间
        return LocalDateTime.now().format(FORMATTER);
        /**
         * MinIO 使用的路径是基于对象存储的，它的路径分隔符标准是 /，与操作系统无关。使用 File.separator 会导致路径在某些系统上生成不正确。
         * 建议：不要替换为 File.separator，保持使用 / 作为路径分隔符。
         */
        // formattedDate = formattedDate.replace("/", File.separator);
    }


    /**
     * 取得对象名称
     * @param md5 文件MD5值
     * @return 对象名称
     */
    public static String getObjectName(String md5){
        return getPathByDate() + "/" + md5;
    }


}
