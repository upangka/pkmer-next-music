package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.convention.page.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectQuery extends PageQuery {
    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "歌曲名称")
    private String songName;
}
