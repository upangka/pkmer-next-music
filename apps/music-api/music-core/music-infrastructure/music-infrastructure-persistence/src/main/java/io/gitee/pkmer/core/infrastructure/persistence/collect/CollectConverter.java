package io.gitee.pkmer.core.infrastructure.persistence.collect;

import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.Collect;
import io.gitee.pkmer.music.domain.collect.CollectAggregate;
import io.gitee.pkmer.music.domain.collect.CollectId;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.user.UserId;
import org.springframework.stereotype.Component;

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
public class CollectConverter {

    /**
     * 将数据库实体转换为领域模型
     */
    public CollectAggregate toDomainModel(Collect collect) {
        return CollectAggregate.builder()
                .id(new CollectId(collect.getId()))
                .userId(new UserId(collect.getUserId()))
                .songId(new SongId(collect.getSongId()))
                .songListId(new SongListId(collect.getSongListId()))
                .type(collect.getType())
                .createTime(collect.getCreateTime())
                .build();
    }


    /**
     * 将领域模型转换为数据库实体
     * 使用set方法
     */
    public Collect toDataModel(CollectAggregate collectAggregate) {
        Collect collect = new Collect();
        if (collectAggregate.getId() != null && collectAggregate.getId().value() != null) {
            collect.setId(collectAggregate.getId().value());
        }
        collect.setUserId(collectAggregate.getUserId().value());
        collect.setSongId(collectAggregate.getSongId().value());
        collect.setSongListId(collectAggregate.getSongListId().value());
        collect.setType(collectAggregate.getType());
        collect.setCreateTime(collectAggregate.getCreateTime());
        return collect;
    }

}
