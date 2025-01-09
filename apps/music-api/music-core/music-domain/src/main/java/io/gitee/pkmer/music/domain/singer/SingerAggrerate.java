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
    private String introduction;
    /**
     * 该歌手的所有歌曲
     */
    private List<SongAggregate> songs;


    public void modifyName(String name) {
        this.name = name;
    }

    public void modifySex(Sex sex) {
        this.sex = sex;
    }

    public void modifyPic(String pic) {
        this.pic = pic;
    }

    public void modifyBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public void modifyLocation(String location) {
        this.location = location;
    }

    public void modifyIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void modifySongs(List<SongAggregate> songs) {
        this.songs = songs;
    }
}
