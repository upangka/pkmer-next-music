package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.ddd.common.ChangingStatus;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.songlist.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public SongListAggregate buildAggregate(SongList songList,
                                            List<ListSong> listSongs) {
        List<BindSongValueObj> bindSongDomainModel = toBindSongDomainModel(listSongs);
        // todo 绑定歌单对应的歌曲
        return songListBuilderFactory.createSongListBuilder()
                .id(songList.getId())
                .pic(songList.getPic())
                .title(songList.getTitle())
                .styles(songList.getStyle())
                .introduction(songList.getIntroduction())
                .songIds(bindSongDomainModel)
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

    public BindSongValueObj toBindSongDomainModel(ListSong listSong){
        BindSongValueObj bindSongValueObj = new BindSongValueObj(
                new SongId(listSong.getSongId()),
                new SongListId(listSong.getSongListId())
        );

        bindSongValueObj.toUnChang();
        return bindSongValueObj;
    }


    public List<BindSongValueObj> toBindSongDomainModel(List<ListSong> listSongs){
        return listSongs.stream().map(this::toBindSongDomainModel)
                .toList();
    }


    /**
     * 获取值对象BindSongValueObj对应数据模型的id
     * @param songIds BindSongValueObj 值对象
     */
    public List<Long> getSongId(List<BindSongValueObj> songIds) {
        List<Long> ids = new ArrayList<>();
        for (BindSongValueObj songId : songIds){
            if(songId.getChangingStatus().equals(ChangingStatus.DELETED)){
                ids.add(songId.getSongId().value());
            }
        }
        return ids;
    }
}
