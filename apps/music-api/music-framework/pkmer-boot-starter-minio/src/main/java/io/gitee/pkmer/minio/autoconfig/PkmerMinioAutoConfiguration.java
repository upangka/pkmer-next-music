package io.gitee.pkmer.minio.autoconfig;

import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.minio.service.BigFileHelper;
import io.gitee.pkmer.minio.service.FileMetaInfoRepository;
import io.gitee.pkmer.minio.repository.mybatis.FileMetaInfoRepositoryImpl;
import io.gitee.pkmer.minio.repository.mybatis.FileMetadataInfoMapper;
import io.gitee.pkmer.minio.service.MinioAdapter;
import io.gitee.pkmer.minio.service.MinioEngineService;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
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
@Slf4j
@AutoConfiguration
@ConditionalOnMissingBean(MinioClient.class)
@EnableConfigurationProperties(PkmerMinioProps.class)
@MapperScan(basePackages = "io.gitee.pkmer.minio.repository.mybatis")
public class PkmerMinioAutoConfiguration {

    /**
     * minio 客户端
     * @param props minio配置信息
     */
    @Bean
    public MinioClient minioClient(PkmerMinioProps props){
        return MinioClient.builder().endpoint(props.getUrl())
                .credentials(props.getAccessKey(), props.getSecretKey()).build();
    }

    /**
     * Minio封装类
     * @param client MinioClient
     */
    @Bean
    public MinioAdapter minioAdapter(MinioClient client){
        return new MinioAdapter(client);
    }

    /**
     * 文件元数据信息仓库
     * @param fileMetadataInfoMapper 文件元数据信息Mapper
     */
    @Bean
    @ConditionalOnMissingBean(FileMetaInfoRepository.class)
    public FileMetaInfoRepository fileMetaInfoRepository(FileMetadataInfoMapper fileMetadataInfoMapper){
        log.info("检测注册{}",fileMetadataInfoMapper);
        return new FileMetaInfoRepositoryImpl(fileMetadataInfoMapper);
    }


    /**
     * minio大文件操作引擎
     */
    @Bean
    public MinioEngineService minioEngineService(PkmerMinioProps props,
                                                 MinioAdapter minioAdapter,
                                                 FileMetaInfoRepository fileMetaInfoRepository){
        log.info("检测注册大文件注册success");
        return new MinioEngineService(
                minioAdapter,
                fileMetaInfoRepository,
                new BigFileHelper(props));
    }
}
