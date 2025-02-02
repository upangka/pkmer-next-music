package io.gitee.pkmer.core.infrastructure.persistence.rank;

import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankList;
import io.gitee.pkmer.music.domain.rank.RankAggregate;
import io.gitee.pkmer.music.domain.rank.RankId;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.user.UserId;
import org.springframework.stereotype.Component;

import java.util.List;

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
@Component
public class RankConverter {

    /**
     * 将领域模型转换为数据模型
     */
    public RankList toDataModel(RankAggregate rankAggregate){
        // 用set方法赋值
        RankList rank = new RankList();
        if(null != rankAggregate.getId()){
            rank.setId(rankAggregate.getId().value());
        }
        rank.setSongListId(rankAggregate.getSongListId().value());
        rank.setConsumerId(rankAggregate.getConsumerId().value());
        rank.setScore(rankAggregate.getScore());
        return rank;
    }

    /**
     * 将数据模型转换为领域模型
     */
    public RankAggregate toDomainModel(RankList rank){
        // 用builder来赋值
        return RankAggregate.builder()
                .id(new RankId(rank.getId()))
                .songListId(new SongListId(rank.getSongListId()))
                .consumerId(new UserId(rank.getConsumerId()))
                .score(rank.getScore())
                .build();
    }

    /**
     * 转化成领域模型集合
     */
    public List<RankAggregate> toDomainModel(List<RankList> rank){
        return rank.stream()
                .map(this::toDomainModel)
                .toList();
    }

    /**
     * 转化成数据模型集合
     */
    public List<RankList> toDataModel(List<RankAggregate> rank){
        return rank.stream()
                .map(this::toDataModel)
                .toList();
    }
}
