package io.gitee.pkmer.music.domain.song.page;

import io.gitee.pkmer.music.domain.song.StyleValidator;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/7
 * </p>
 */
@Component
public class SongListPageBuilderFactory {
    private final StyleValidator styleValidator;
    public SongListPageBuilderFactory(StyleValidator styleValidator){
        this.styleValidator = styleValidator;
    }

    public SongListPageBuilder createBuilder(){
        return new SongListPageBuilder(this.styleValidator);
    }
}
