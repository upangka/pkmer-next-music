package io.gitee.pkmer.music.domain.song.page;

import io.gitee.pkmer.convention.page.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@Data
public class SongListPage extends PageQuery {
    private String style;

    public long offset(){
        return (long)(super.getPageNo() - 1) * super.getPageSize();
    }

}
