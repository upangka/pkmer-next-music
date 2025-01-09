package io.gitee.pkmer.core.infrastructure.persistence.song;

import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.music.domain.song.SingerId;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import io.gitee.pkmer.music.domain.song.SongId;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/9
 * </p>
 */
@Component
public class SongConverter {

    public Song covertFrom(SongAggregate aggregateRoot){

        // 用set方法来赋值
        Song song = new Song();
        song.setSingerId(aggregateRoot.getSingerId().value());
        song.setName(aggregateRoot.getName());
        song.setIntroduction(aggregateRoot.getIntroduction());
        song.setCreateTime(aggregateRoot.getCreateTime());
        song.setUpdateTime(aggregateRoot.getUpdateTime());
        song.setPic(aggregateRoot.getPic());
        song.setUrl(aggregateRoot.getUrl());
        song.setLyric(aggregateRoot.getLyric());
        return song;
    }

    public SongAggregate covertTo(Song song){
        // 生成SongAggregate对象
        SongAggregate songAggregate = new SongAggregate(
                new SongId(song.getId()),
                new SingerId(song.getSingerId()),
                song.getName(),
                song.getIntroduction(),
                song.getPic(),
                song.getUrl(),
                song.getLyric()
        );

        return songAggregate;
    }
}
