package io.gitee.pkmer.music.web.songlist.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/6
 */
@Data
public class AddSongListReq {
    @Schema(title = "id", description = "歌单id为空")
    private Integer id;
    @Schema(title = "title", description = "歌单标题")
    @NotBlank(message = "歌单标题不能为空")
    private String title;
    @Schema(title = "pic", description = "歌单封面")
    @NotBlank(message = "歌单封面不能为空")
    private String pic;
    @Schema(title = "style", description = "歌单风格")
    @NotBlank(message = "歌单风格不能为空")
    private String style;
    @Schema(title = "introduction", description = "歌单介绍")
    @NotBlank(message = "歌单介绍不能为空")
    private String introduction;
}
