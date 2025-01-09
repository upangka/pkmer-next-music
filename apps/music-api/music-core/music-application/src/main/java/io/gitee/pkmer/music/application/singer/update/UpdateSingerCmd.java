package io.gitee.pkmer.music.application.singer.update;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

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
public class UpdateSingerCmd implements Command {

    @Schema(description = "歌手id")
    @NotNull(message = "歌手id不能为空")
    private Long id;

    @Schema(description = "歌手名称")
    @NotBlank(message = "歌手名称不能为空")
    private String name;

    @Schema(description = "歌手性别")
    @NotNull(message = "歌手性别不能为空")
    private Byte sex;

    @Schema(description = "歌手图片")
    @NotBlank(message = "歌手图片不能为空")
    private String pic;

    @Schema(description = "歌手生日")
    @NotNull(message = "歌手生日不能为空")
    private LocalDateTime birth;

    @Schema(description = "歌手地区")
    @NotBlank(message = "歌手地区不能为空")
    private String location;

    @Schema(description = "歌手简介")
    @NotBlank(message = "歌手简介不能为空")
    private String introduction;
}
