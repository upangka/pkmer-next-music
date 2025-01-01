package io.gitee.pkmer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * At 2024/12/31
 */
@SpringBootApplication
@MapperScan("io.gitee.pkmer.mapper")
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MusicApplication.class);
        app.run(args);
    }
}
