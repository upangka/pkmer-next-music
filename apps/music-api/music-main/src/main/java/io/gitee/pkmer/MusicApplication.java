package io.gitee.pkmer;


import io.gitee.pkmer.minio.repository.mybatis.FileMetadataInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * At 2024/12/31
 */
@Slf4j
@SpringBootApplication
public class MusicApplication{

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MusicApplication.class);
        app.run(args);
    }

}
