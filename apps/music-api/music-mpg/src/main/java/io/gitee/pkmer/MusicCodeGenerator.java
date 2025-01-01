package io.gitee.pkmer;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/1
 */
public class MusicCodeGenerator {
    private final static String resource = "jdbc.properties";
    public static void main(String[] args) {

        Properties props = loadProperties();
        FastAutoGenerator.create(props.getProperty("url"),props.getProperty("username"),props.getProperty("password"))
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .entityBuilder()
                        .enableLombok()
                        .build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 加载数据库配置信息
     */
    protected  static Properties loadProperties() {
        URL url = MusicCodeGenerator.class.getClassLoader().getResource(resource);
        Properties properties = new Properties();
        try(InputStream is = url.openStream()){
            properties.load(is);
            System.out.println(properties.get("url"));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return properties;
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
