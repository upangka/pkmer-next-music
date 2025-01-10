package io.gitee.pkmer.music.application.collect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

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
@Getter
@Builder
public class CollectView {

    @Schema(description = "收藏id")
    private Long id;

    @Schema(description = "收藏类型")
    private String type;

    @Schema(description = "歌曲id")
    private Long songId;

    @Schema(description = "歌单id")
    private Long songListId;

    @Schema(description = "收藏时间")
    private LocalDateTime createTime;
}
