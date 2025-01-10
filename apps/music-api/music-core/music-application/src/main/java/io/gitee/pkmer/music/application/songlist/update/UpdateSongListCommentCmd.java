package io.gitee.pkmer.music.application.songlist.update;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
@Data
public class UpdateSongListCommentCmd implements Command {
    @Schema(description = "歌单id")
    @NotNull(message = "歌单id不能为空")
    private Long songListId;
    @Schema(description = "评论id")
    @NotNull(message = "评论id不能为空")
    private Long commentId;
    @Schema(description = "评论内容")
    @NotBlank
    private String content;
}
