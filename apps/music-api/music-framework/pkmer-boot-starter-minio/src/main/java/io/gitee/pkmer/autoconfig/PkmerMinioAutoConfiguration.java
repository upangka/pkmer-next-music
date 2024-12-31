package io.gitee.pkmer.autoconfig;

import io.gitee.pkmer.props.PkmerMinioProps;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@AutoConfiguration
@ConditionalOnMissingBean(MinioClient.class)
@EnableConfigurationProperties(PkmerMinioProps.class)
public class PkmerMinioAutoConfiguration {

    @Bean
    public MinioClient minioClient(PkmerMinioProps props){
        return MinioClient.builder().endpoint(props.getUrl())
                .credentials(props.getAccessKey(), props.getSecretKey()).build();
    }
}
