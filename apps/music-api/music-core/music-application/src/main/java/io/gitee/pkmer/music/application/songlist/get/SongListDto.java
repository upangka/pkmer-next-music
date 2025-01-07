package io.gitee.pkmer.music.application.songlist.get;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
@Builder
public class SongListDto{
    @Schema(description = "歌单id")
    private String id;

    @Schema(description = "歌单标题")
    private String title;

    @Schema(description = "歌单封面")
    private String pic;

    @Schema(description = "歌单风格")
    private List<String> styles;

    @Schema(description = "歌单介绍")
    private String introduction;
}
