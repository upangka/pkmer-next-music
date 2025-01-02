package io.gitee.pkmer;

import io.gitee.pkmer.core.dao.mapper.banner.BannerDynamicMapper;
import io.gitee.pkmer.core.dao.mapper.banner.BannerDynamicSqlSupport;
import io.gitee.pkmer.core.dao.model.banner.Banner;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mybatis.dynamic.sql.SqlBuilder.isNotNull;
import static org.mybatis.dynamic.sql.util.kotlin.elements.SqlElementsKt.isNull;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * At 2024/12/31
 */
@SpringBootApplication
public class MusicApplication {
    @Autowired
    private BannerDynamicMapper bannerDynamicMapper;
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MusicApplication.class);
        app.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        Banner banner = new Banner();
        banner.setPic("yyy");
        return args -> {
            System.out.println( bannerDynamicMapper.insert(banner));
            System.out.println( bannerDynamicMapper.count(c->c));
            System.out.println( bannerDynamicMapper.count(c->c.where(BannerDynamicSqlSupport.id,isNotNull())));
            System.out.println( bannerDynamicMapper.count(CountDSLCompleter.allRows()));
        };
    }
}
