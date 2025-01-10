package io.gitee.pkmer.music.domain.song;

import io.gitee.pkmer.ddd.EntityAggregate;
import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.music.domain.comment.CommentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
@EqualsAndHashCode(callSuper = true)
@Getter
public class SongAggregate extends AuditableEntity implements EntityAggregate {
    private SongId id;
    private SingerId singerId;

    private String name;

    private String introduction;

    private String pic;

    private String url;

    private String lyric;

    /**
     * 歌曲的评论
     */
    private List<CommentEntity> comments;

    public SongAggregate(SongId id, SingerId singerId, String name, String introduction, String pic, String url, String lyric) {
        this.id = id;
        this.singerId = singerId;
        this.name = name;
        this.introduction = introduction;
        this.pic = pic;
        this.url = url;
        this.lyric = lyric;
    }


    public void modifyName(String name) {
        this.name = name;
    }

    public void modifyIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void modifyPic(String pic) {
        this.pic = pic;
    }

    public void modifyUrl(String url) {
        this.url = url;
    }

    public void modifyLyric(String lyric) {
        this.lyric = lyric;
    }
}
