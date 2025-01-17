package io.gitee.pkmer.minio.props;

import lombok.Data;
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
}
