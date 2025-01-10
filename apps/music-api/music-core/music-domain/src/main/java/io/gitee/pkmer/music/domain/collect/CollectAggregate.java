package io.gitee.pkmer.music.domain.collect;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

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
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
public class CollectAggregate extends AuditableEntity implements AggregateRoot {
    private CollectId id;
    private UserId userId;
    private SongListId songListId;
    private SongId songId;
    private Byte type;
    private LocalDateTime createTime;

    public void cancel(){
        this.toDelete();
    }

    public void collectSong(SongId songId){
        this.songId = songId;
        recordTime();
    }

    public void collectSongList(SongListId songListId){
        this.songListId = songListId;
        recordTime();
    }

    private void recordTime(){
        this.createTime = LocalDateTime.now();
    }

}
