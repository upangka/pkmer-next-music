package io.gitee.pkmer;



import io.gitee.pkmer.mybatis.generator.MyBatisGeneratorRunner;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.FileSystems;


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
