package io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis;

import io.gitee.pkmer.mybatis.annotations.SnowflakeId;
import lombok.Data;

/**
 * generated by MyBatis Generator.
 * rank_list(歌单排行榜)
 * 
 * @author 胖卡
 * @version 1.0.0
 * @date 2025/01/10
 */
@Data
public class RankList {
    @SnowflakeId
    private Long id;
    private Long songListId;
    private Long consumerId;
    private Integer score;
}