package io.gitee.pkmer.minio.props;

import io.gitee.pkmer.minio.utils.TimeMinutesUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Minio 配置类
 *
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@ConfigurationProperties(prefix = "pkmer.minio")
@Data
public class PkmerMinioProps {

    /**
     * 对象存储服务的URL
     */
    private String url;
    /**
     * 账户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;

    /**
     * 默认分片大小5MB
     */
    private String defaultChunkSize = "5MB";

    /**
     * 默认过期时间1小时
     * 如： 配置60m 代表一小时,或者 1h
     * 支持的单位 m h
     */
    private String expires = "1h";

    public int getExpiresOfMinutes() {
        return TimeMinutesUtil.getTime(expires);
    }
}
