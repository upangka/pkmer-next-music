package io.gitee.pkmer.core.infrastructure.persistence.song;

import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.music.domain.song.SingerId;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import io.gitee.pkmer.music.domain.song.SongId;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

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

    /**
     * 转化成数据模型
     */
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

        songAggregate.toUnChang();
        return songAggregate;
    }

    /**
     * 转化成领域模型
     */
    public List<SongAggregate> toDomainList(List<Song> songs){
        if(songs == null || songs.isEmpty()) return Collections.emptyList();
        return  songs.stream()
                .map(this::covertTo)
                .toList();
    }

    /**
     * 转化成数据模型
     */
    public List<Song> toDataList(List<SongAggregate> songAggregates){
        if(songAggregates == null || songAggregates.isEmpty()) return Collections.emptyList();
        return songAggregates.stream()
                .map(this::covertFrom)
                .toList();
    }
}
