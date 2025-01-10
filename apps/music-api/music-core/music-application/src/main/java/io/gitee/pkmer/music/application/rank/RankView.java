package io.gitee.pkmer.music.application.rank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
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
@Builder
public class RankView {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "歌单id")
    private Long songListId;
    @Schema(description = "歌单名称")
    private String songListName;
    @Schema(description = "用户id")
    private Long consumerId;
    @Schema(description = "评分")
    private Integer score;
}
