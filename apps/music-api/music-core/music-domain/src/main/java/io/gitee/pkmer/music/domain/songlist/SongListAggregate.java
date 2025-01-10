package io.gitee.pkmer.music.domain.songlist;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import io.gitee.pkmer.music.domain.comment.CommentEntity;
import io.gitee.pkmer.music.domain.song.SongId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
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

    private List<BindSongValueObj> songIds;

    /**
     * 歌单的评论
     */
    private List<CommentEntity> comments;

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
        songIds.stream().filter(song -> song.getSongId().value().equals(songId))
                .findFirst()
                .ifPresent(AuditableEntity::toDelete);
    }

    /**
     * 更新评论
     * @param commentId 评论id
     * @param content 评论内容
     */
    public void modifyComment(Long commentId,String content){
        // todo 完成登录功能之后处理&& comment.getUserId().value() == userId
        CommentEntity commentEntity = comments.stream().filter(comment ->
                        comment.getId().equals(commentId))
                .findFirst().orElseThrow();
        commentEntity.setContent(content);
        commentEntity.toUpdate();
    }

    public void deleteComment(Long commentId){
        // todo 完成登录功能之后处理&& comment.getUserId().value() == userId
        CommentEntity commentEntity = comments.stream().filter(comment ->
                        comment.getId().equals(commentId))
                .findFirst().orElseThrow();
        commentEntity.toDelete();
    }

    /**
     * 添加评论
     */
    public void addComment(String content){
        // todo 完成登录功能之后处理 添加userId
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentEntity.setCreateTime(LocalDateTime.now());
        comments.add(commentEntity);
    }

}