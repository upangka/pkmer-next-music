package io.gitee.pkmer.music.domain.songlist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/6
 */
@Slf4j
@Component
public class StyleValidator {
    public void validate(String style) {
        String[] styles = style.split("-");
        for(String s: styles){
            boolean isExist = Arrays.stream(Style.values()).anyMatch(
                    item -> item.getDesc().equals(s));
            if(!isExist){
                log.error("Invalid style: {}",s);
                throw new RuntimeException("Invalid style: "+s);
            }
        }
    }
}
