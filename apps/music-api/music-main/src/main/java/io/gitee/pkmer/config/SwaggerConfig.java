package io.gitee.pkmer.config;

//import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {
    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags()!=null){
                // 遍历标签并添加x-order属性
                openApi.getTags().forEach(tag -> {
                    // 创建扩展属性map并添加x-order
                    Map<String,Object> map=new HashMap<>();
                    map.put("x-order", "hzz");
                    tag.setExtensions(map);
                });
            }
            // 为路径添加扩展属性
            if(openApi.getPaths()!=null){
                // 添加全局扩展属性
                openApi.addExtension("x-test123","333");
                // 为路径添加特定的扩展属性
                openApi.getPaths().addExtension("x-abb",111);
            }

        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pkmer Music系统API")
                        .version("1.0")
                        .description( "Pkmer Next Music 后端API")
                        .termsOfService("https://gitee.com/pkmer/pkmer-next-music")
                        .license(new License().name("Apache 2.0")
                                .url("https://gitee.com/pkmer/pkmer-next-music")));
    }


}