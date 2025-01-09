package io.gitee.pkmer.music.domain.singer;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
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
@Builder
@Getter
public class SingerAggrerate extends AuditableEntity implements AggregateRoot {
    private SingerId singerId;
    private String name;
    private Sex sex;
    private String pic;
    private LocalDateTime birth;
    private String location;
    /**
     * 该歌手的所有歌曲
     */
    private List<SongAggregate> songs;
}
