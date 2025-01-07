package io.gitee.pkmer.music.domain.song.page;

import io.gitee.pkmer.music.domain.song.StyleValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Getter
@Setter
@Accessors(chain = true)
public class SongListPageBuilder {
    private String style;
    private int pageNo;
    private int pageSize;

    private final StyleValidator styleValidator;
    SongListPageBuilder(StyleValidator styleValidator){
        this.styleValidator = styleValidator;
    }

    public SongListPage build(){
        styleValidator.validate(style);
        SongListPage songListPage = new SongListPage();
        songListPage.setStyle(style);
        songListPage.setPageNo(pageNo);
        songListPage.setPageSize(pageSize);
        return songListPage;
    }

}
