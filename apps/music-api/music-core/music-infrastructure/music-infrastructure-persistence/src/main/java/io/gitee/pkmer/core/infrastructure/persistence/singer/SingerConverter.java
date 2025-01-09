package io.gitee.pkmer.core.infrastructure.persistence.singer;

import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.Singer;
import io.gitee.pkmer.core.infrastructure.persistence.song.SongConverter;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.music.domain.singer.Sex;
import io.gitee.pkmer.music.domain.singer.SingerAggrerate;
import io.gitee.pkmer.music.domain.singer.SingerId;
import io.gitee.pkmer.music.domain.song.SongAggregate;
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
public class SingerConverter {
    private final SongConverter songConverter;

    public SingerConverter(SongConverter songConverter){
        this.songConverter = songConverter;
    }
    /**
     * 转换成数据模型
     */
    public Singer convertFrom(SingerAggrerate singerAggrerate){
        Singer singer = new Singer();
        singer.setName(singerAggrerate.getName());
        singer.setSex(singerAggrerate.getSex().getValue());
        singer.setPic(singerAggrerate.getPic());
        singer.setBirth(singerAggrerate.getBirth());
        singer.setLocation(singerAggrerate.getLocation());
        return singer;
    }

    /**
     * 转换领域模型
     */
    public SingerAggrerate convertTo(Singer singer, List<Song> songs){

        List<SongAggregate> songList = songConverter.toDomainList(songs);

        SingerAggrerate singerAggrerate = SingerAggrerate.builder()
                .singerId(new SingerId(singer.getId()))
                .name(singer.getName())
                .sex(Sex.valueOf(singer.getSex()))
                .pic(singer.getPic())
                .birth(singer.getBirth())
                .location(singer.getLocation())
                .songs(songList)
                .build();

        singerAggrerate.toUnChang();

        return singerAggrerate;
    }

    public SongConverter getSongConverter(){
        return songConverter;
    }
}
