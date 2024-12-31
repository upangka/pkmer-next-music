package io.gitee.pkmer.autoconfig;

import io.gitee.pkmer.props.PkmerMinioProps;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2024/12/31
 */
@AutoConfiguration
@EnableConfigurationProperties(PkmerMinioProps.class)
public class PkmerMinioAutoConfiguration {

}
