package io.gitee.pkmer.music.application.singer.add;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.gitee.pkmer.ddd.shared.command.Command;
import io.gitee.pkmer.music.domain.singer.Sex;
import io.gitee.pkmer.music.domain.singer.SexDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.util.Assert;

import java.time.LocalDate;
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
public class AddSingerCmd implements Command {

    @Schema(description = "歌手名称")
    @NotBlank(message = "歌手名称不能为空")
    private String name;

    @Schema(description = "歌手性别")
    @NotNull(message = "歌手性别不能为空")
    @JsonDeserialize(using = SexDeserializer.class)
    private Sex sex;

    @Schema(description = "歌手图片")
    private String pic;

    @Schema(description = "歌手生日")
    @NotNull(message = "歌手生日不能为空")
    private LocalDate birth;

    @Schema(description = "歌手地区")
    @NotBlank(message = "歌手地区不能为空")
    private String location;

    @Schema(description = "歌手简介")
    @NotBlank(message = "歌手简介不能为空")
    private String introduction;

}
