package io.gitee.pkmer.music.application.rank;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class AddRankCmd implements Command {

    @Schema(description = "歌单id")
    @NotNull(message = "歌单id不能为空")
    private Long songListId;
    @Schema(description = "用户id",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long consumerId;
    @Schema(description = "评分")
    @NotNull(message = "评分不能为空")
    private Integer score;
}
