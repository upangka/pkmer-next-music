package io.gitee.pkmer;



import io.gitee.pkmer.mybatis.generator.MyBatisGeneratorRunner;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
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
@Slf4j
public class MusicCodeGenerator {
    public static void main(String[] args){
        log.info("start");
        MyBatisGeneratorRunner.genCode();
        log.info("代码生成在 {}",getWorkingDirectory());
        log.info("End");
    }


    public static String getWorkingDirectory(){
        return FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();
    }

}
