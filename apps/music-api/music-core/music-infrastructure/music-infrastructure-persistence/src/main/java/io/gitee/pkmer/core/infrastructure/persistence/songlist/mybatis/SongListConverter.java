package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.music.domain.songlist.BindSongValueObj;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListBuilderFactory;
import io.gitee.pkmer.music.domain.songlist.Style;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/7
 * </p>
 */
@Component
public class SongListConverter {
    private final SongListBuilderFactory songListBuilderFactory;

    public SongListConverter( SongListBuilderFactory songListBuilderFactory){
        this.songListBuilderFactory = songListBuilderFactory;
    }

    /**
     * 构建领域对象
     * @param songList
     */
    public SongListAggregate buildAggregate(SongList songList) {
        // todo 绑定歌单对应的歌曲
        return songListBuilderFactory.createSongListBuilder()
                .id(songList.getId())
                .pic(songList.getPic())
                .title(songList.getTitle())
                .styles(songList.getStyle())
                .introduction(songList.getIntroduction())
                .build();
    }


    /**
     * 转换领域对象为对应的表实体
     * todo return 返回与歌单相关的歌曲
     */
    public SongList convertFrom(SongListAggregate aggregate){
        SongList record = new SongList();
        List<Style> styles = aggregate.getStyles();
        String style = styles.stream().map(Style::getDesc)
                .collect(Collectors.joining("-"));
        record.setId(aggregate.getId().value());
        record.setTitle(aggregate.getTitle());
        record.setPic(aggregate.getPic());
        record.setStyle(style);
        record.setIntroduction(aggregate.getIntroduction());
        return record;
    }


    public ListSong toListSongDataModel(BindSongValueObj songId) {
        ListSong record = new ListSong();
        record.setSongId(songId.getSongId().value());
        record.setSongListId(songId.getSongListId().value());
        return record;
    }

    public List<ListSong> toListSongDataModel(List<BindSongValueObj> songIds) {
        return songIds.stream().map(this::toListSongDataModel)
                .toList();
    }
}
