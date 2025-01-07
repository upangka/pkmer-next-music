package io.gitee.pkmer.music.application.songlist.update;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
public class UpdateSongListCmd implements Command {

    /**
     * 歌单基本信息
     */
    public interface SongListBase{}

    /**
     * 歌单封面
     */
    public interface SongListPic{}

    @Schema(description = "歌单id")
    @NotNull(message = "歌单id不能为空", groups = {SongListBase.class, SongListPic.class})
    private Long id;

    @Schema(description = "歌单封面")
    @NotBlank(message = "歌单封面不能为空",groups = {SongListBase.class, SongListPic.class})
    private String pic;

    @Schema(description = "歌单标题")
    @NotBlank(message = "歌单标题不能为空",groups = {SongListBase.class})
    private String title;

    @Schema(description = "歌单风格", example = "华语-BGM")
    @NotBlank(message = "歌单风格不能为空",groups = {SongListBase.class})
    private String styles;

    @Schema(description = "歌单介绍")
    @NotBlank(message = "歌单介绍不能为空",groups = {SongListBase.class})
    private String introduction;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean isUpdatePic;


    public void toUpdatePic(){
        this.isUpdatePic = true;
    }

    public void toBaseInfo(){
        this.isUpdatePic = false;
    }

    public boolean isToModifyPic(){
        return this.isUpdatePic;
    }
}
