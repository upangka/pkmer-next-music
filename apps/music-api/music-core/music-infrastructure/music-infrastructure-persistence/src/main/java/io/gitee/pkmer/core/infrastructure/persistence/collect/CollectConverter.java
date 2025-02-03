package io.gitee.pkmer.core.infrastructure.persistence.collect;

import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.Collect;
import io.gitee.pkmer.ddd.id.Identifier;
import io.gitee.pkmer.music.domain.collect.CollectAggregate;
import io.gitee.pkmer.music.domain.collect.CollectId;
import io.gitee.pkmer.music.domain.enums.SongAndListType;
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

        SongAndListType type = SongAndListType.valueOf(collect.getType());

        return CollectAggregate.builder()
                .id(new CollectId(collect.getId()))
                .userId(new UserId(collect.getUserId()))
                .songId(new SongId(collect.getSongId()))
                .songListId(new SongListId(collect.getSongListId()))
                .type(type)
                .createTime(collect.getCreateTime())
                .build();
    }


    /**
     * 将领域模型转换为数据库实体
     * 使用set方法
     */
    public Collect toDataModel(CollectAggregate collectAggregate) {
        Collect collect = new Collect();
        if (idIsNotNull(collectAggregate.getId())) {
            collect.setId(collectAggregate.getId().value());
        }
        if(idIsNotNull(collectAggregate.getUserId())){
            collect.setUserId(collectAggregate.getUserId().value());
        }
        if(idIsNotNull(collectAggregate.getSongId())){
            collect.setSongId(collectAggregate.getSongId().value());
        }
        if(idIsNotNull(collectAggregate.getSongListId())){
            collect.setSongListId(collectAggregate.getSongListId().value());
        }
        if(collectAggregate.getType() != null){
            collect.setType(collectAggregate.getType().getValue());
        }

        collect.setCreateTime(collectAggregate.getCreateTime());
        return collect;
    }

    private boolean idIsNotNull(Identifier<Long> id){
        return id != null && id.value() != null;
    }



}
