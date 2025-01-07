package io.gitee.pkmer.music.application.songlist.get;

import io.gitee.pkmer.convention.page.query.PageQuery;
import io.gitee.pkmer.ddd.shared.command.Command;
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
 * @since 2025/1/7
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SongListPageQueryCmd extends PageQuery implements Command {
    @Schema(description = "歌单风格")
    private String style;
}
