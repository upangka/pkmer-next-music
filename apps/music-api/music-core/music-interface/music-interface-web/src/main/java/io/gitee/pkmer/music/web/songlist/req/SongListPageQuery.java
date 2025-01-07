package io.gitee.pkmer.music.web.songlist.req;

import io.gitee.pkmer.convention.page.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
@Data
public class SongListPageQuery extends PageQuery {
    @Schema(title = "风格")
    private String style;
}
