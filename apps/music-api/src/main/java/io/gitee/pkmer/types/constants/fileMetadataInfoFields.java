package io.gitee.pkmer.types.constants;

/**
* This class contains the database column names for the file_metadata_info table.
* Generator by TableFields.ftl
*
* @author 胖卡
* @date 2025-01-18
*/
public class fileMetadataInfoFields {

    /**
     * 主键
     */
    public static final String ID = "id";

    /**
     * 文件KEY
     */
    public static final String FILE_KEY = "file_key";

    /**
     * 文件md5
     */
    public static final String FILE_MD5 = "file_md5";

    /**
     * 文件名
     */
    public static final String FILE_NAME = "file_name";

    /**
     * MIME类型
     */
    public static final String FILE_MIME_TYPE = "file_mime_type";

    /**
     * 文件后缀
     */
    public static final String FILE_SUFFIX = "file_suffix";

    /**
     * 文件大小
     */
    public static final String FILE_SIZE = "file_size";

    /**
     * 预览图 0:无 1:有
     */
    public static final String IS_PREVIEW = "is_preview";

    /**
     * 是否私有 0:否 1:是
     */
    public static final String IS_PRIVATE = "is_private";

    /**
     * 存储桶
     */
    public static final String BUCKET = "bucket";

    /**
     * 存储桶路径
     */
    public static final String BUCKET_PATH = "bucket_path";

    /**
     * 上传任务id
     */
    public static final String UPLOAD_ID = "upload_id";

    /**
     * 文件的etag
     */
    public static final String ETAG = "etag";

    /**
     * 状态 0:未完成 1:已完成
     */
    public static final String IS_FINISHED = "is_finished";

    /**
     * 是否分块 0:否 1:是
     */
    public static final String IS_PART = "is_part";

    /**
     * 分块数量
     */
    public static final String PART_NUMBER = "part_number";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "create_time";

    /**
     * 创建用户
     */
    public static final String CREATE_USER = "create_user";

    /**
     * 更新时间
     */
    public static final String UPDATE_TIME = "update_time";

    /**
     * 更新用户
     */
    public static final String UPDATE_USER = "update_user";
}