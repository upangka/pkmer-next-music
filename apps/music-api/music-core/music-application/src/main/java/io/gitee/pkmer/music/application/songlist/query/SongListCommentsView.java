package io.gitee.pkmer.music.application.songlist.query;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class SongListCommentsView {
    @Schema(description = "评论id")
    private Long id;
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "评论内容")
    private String content;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "点赞数")
    private Integer up;
}
