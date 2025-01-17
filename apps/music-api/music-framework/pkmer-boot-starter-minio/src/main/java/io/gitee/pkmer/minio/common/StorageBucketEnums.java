package io.gitee.pkmer.minio.common;

import java.util.List;

public enum StorageBucketEnums {
    /**
     * 桶策略枚举类
     * 文档（document）：txt、rtf、ofd、doc、docx、xls、xlsx、ppt、pptx、pdf
     * 压缩包（package）：zip、rar、7z、tar、wim、gz、bz2
     * 音频（ audio ） ：mp3、wav、flac、acc、ogg、aiff、m4a、wma、midi
     * 视频（ video ） ：mp4、avi、mov、wmv、flv、mkv、mpeg、mpg 、rmvb
     * 图片 – 原始（ image ） ：jpeg、jpg、png、bmp、webp、gif
     * 图片 – 缩略图（ image-preview ） ：按照宽度 300 像素压缩
     * 其他（ other ） ：未在上述格式中的文件
     * 其他规则：文件在桶中存储时，按照 /年/月 划分路径
     * 用以规避Linux ext3文件系统下单个目录最多创建32000个目录的问题，参考了阿里云的处理办法
     */
    DOCUMENT("document", "文档文件桶", List.of("txt", "rtf", "ofd", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf")),
    PACKAGE("package", "压缩文件桶", List.of("zip", "rar", "7z", "tar", "wim", "gz", "bz2")),
    AUDIO("audio", "音频文件桶", List.of("mp3", "wav", "flac", "acc", "ogg", "aiff", "m4a", "wma", "midi")),
    VIDEO("video", "视频文件桶", List.of("mp4", "avi", "mov", "wmv", "flv", "mkv", "mpeg", "mpg", "rmvb")),
    IMAGE("image", "图片文件桶", List.of("jpeg", "jpg", "png", "bmp", "webp", "gif")),
    IMAGE_PREVIEW("image-preview", "缩略图文件桶", List.of("jpeg_preview", "jpg_preview", "png_preview", "bmp_preview", "webp_preview", "gif_preview")),
    OTHER("other", "其他文件桶", List.of("*"));


    private final String bucketName;
    private final String desc;
    private final List<String> fileSuffixs;

    StorageBucketEnums(String bucketName, String desc, List<String> fileSuffixs) {

        this.bucketName = bucketName;
        this.desc = desc;
        this.fileSuffixs = fileSuffixs;
    }

    /**
     * 根据文件后缀获取对应的存储桶名称
     *
     * @param fileSuffix 文件后缀，用于确定存储桶类型
     * @return 返回与文件后缀匹配的存储桶名称，如果没有匹配的存储桶，则返回默认存储桶名称
     */
    public static String getBucketNameByFileSuffix(String fileSuffix) {
        for (StorageBucketEnums bucketEnum : values()) {
            if (bucketEnum.fileSuffixs.contains(fileSuffix)) {
                return bucketEnum.bucketName;
            }
        }
        return OTHER.bucketName;
    }
}
