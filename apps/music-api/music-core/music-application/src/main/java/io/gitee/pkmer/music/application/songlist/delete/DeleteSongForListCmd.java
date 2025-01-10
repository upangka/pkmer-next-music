package io.gitee.pkmer.music.application.songlist.delete;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
public class DeleteSongForListCmd implements Command {
    @Schema(description = "歌单id")
    @NotNull(message = "歌单id不能为空")
    Long songListId;
    @Schema(description = "歌曲id")
    @Min(value = 1, message = "歌曲id不能为空")
    List<Long> songIds = new ArrayList<>();
}
