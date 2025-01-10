package io.gitee.pkmer.music.domain.songlist;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import io.gitee.pkmer.music.domain.song.SongId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 歌单与歌曲的绑定关系
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Getter
@Builder
@AllArgsConstructor
public class SongListAggregate extends AuditableEntity implements AggregateRoot {
    private SongListId id;

    private String title;

    private String pic;

    @Builder.Default
    private List<Style> styles = Collections.emptyList();

    private String introduction;

    private List<BindSongValueObj> songIds = new ArrayList<>();

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void modifyPic(String pic) {
        this.pic = pic;
    }

    public void modifyStyles(String styles) {
        this.styles =  Style.toStyles(styles);
    }

    public void modifyIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<BindSongValueObj> getBindSongs(){
        return Collections.unmodifiableList(this.songIds);
    }

    /**
     * 添加歌曲
     */
    public void addSong(Long songId){
        BindSongValueObj bindSong = new BindSongValueObj(
                new SongId(songId),this.id);

        this.songIds.add(bindSong);
    }

    /**
     * 删除歌曲
     */
    public void deleteSong(final Long songId){
        songIds.removeIf(bindSong -> bindSong.getSongId().value().equals(songId));
    }

}