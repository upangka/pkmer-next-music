package io.gitee.pkmer.music.application.song.update;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

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
@Getter
@Value(staticConstructor = "commandOf")
public class UpdateSongCmd implements Command {
    @Schema(description = "歌曲id")
    @NotNull(message = "歌曲id不能为空")
    Long id;

    @Schema(description = "歌手id")
    @NotNull(message = "歌手id不能为空")
    Long singerId;

    @Schema(description = "歌曲名称")
    @NotBlank(message = "歌曲名称不能为空")
    String name;

    @Schema(description = "歌曲简介")
    @NotBlank(message = "歌曲简介不能为空")
    String introduction;

    @Schema(description = "歌曲封面")
    @NotBlank(message = "歌曲封面不能为空")
    String pic;

    @Schema(description = "歌曲地址")
    @NotBlank(message = "歌曲地址不能为空")
    String url;

    @Schema(description = "歌词")
    @NotBlank(message = "歌词不能为空")
    String lyric;
}
