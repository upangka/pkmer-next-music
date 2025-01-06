package io.gitee.pkmer.music.domain.song;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/6
 */
@Component
public class SongListBuilderFactory {
    private final StyleValidator styleValidator;
    public SongListBuilderFactory(StyleValidator styleValidator){
        this.styleValidator = styleValidator;
    }
    public  SongListBuilder createSongListBuilder(){
        return new SongListBuilder(styleValidator);
    }
}
