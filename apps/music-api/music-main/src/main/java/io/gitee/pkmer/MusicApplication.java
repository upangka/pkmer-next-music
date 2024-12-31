package io.gitee.pkmer;

import io.gitee.pkmer.props.PkmerMinioProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * At 2024/12/31
 */
@SpringBootApplication
@RestController
@RequestMapping
public class MusicApplication {
    @Autowired
    private PkmerMinioProps minioProps;
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MusicApplication.class);
        app.run(args);
    }

    @GetMapping("/")
    public String hello(){

        return "hello world" + minioProps.getSecretKey();
    }
}
