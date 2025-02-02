package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.Comment;
import io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.CommentDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.CommentDynamicSqlSupport;
import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankList;
import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankListDynamicSqlSupport;
import io.gitee.pkmer.ddd.common.ChangingStatus;
import io.gitee.pkmer.music.domain.comment.CommentEntity;
import io.gitee.pkmer.music.domain.songlist.BindSongValueObj;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.id;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Slf4j
@Repository
public class SongListRepositoryImpl implements SongListRepository {
    private final SongListDynamicMapper songListMapper;
    private final ListSongDynamicMapper listSongMapper;
    private final CommentDynamicMapper commentMapper;
    private final RankDynamicMapper rankMapper;
    private final SongListConverter converter;

    public SongListRepositoryImpl(SongListDynamicMapper songListDynamicMapper,
                                  ListSongDynamicMapper listSongDynamicMapper,
                                  CommentDynamicMapper commentDynamicMapper,
                                  RankDynamicMapper rankDynamicMapper,
                                  SongListConverter converter) {
        this.songListMapper = songListDynamicMapper;
        this.listSongMapper = listSongDynamicMapper;
        this.commentMapper = commentDynamicMapper;
        this.rankMapper = rankDynamicMapper;
        this.converter = converter;
    }

    @Override
    public Optional<SongListAggregate> load(SongListId songListId) {
        // 处理歌单
        SongList songList = songListMapper.selectOne(
                        c -> c.where(id, isEqualTo(songListId.value())))
                .orElseThrow();


        List<ListSong> songIds = listSongMapper.select(c ->
                c.where(id, isEqualTo(songList.getId())));

        // 处理评论
        List<Comment> comments = commentMapper.select(c ->
                c.where(CommentDynamicSqlSupport.songListId, isEqualTo(songList.getId())));

        double sumScore = getSumScore(songList);

        return Optional.of(
                converter.buildAggregate(songList, songIds, comments,sumScore)
        );

    }

    private double getSumScore(SongList songList) {
        // 处理歌单的评分
        SelectStatementProvider selectSumScore = select(sum(RankListDynamicSqlSupport.score).as("score"))
                .from(RankListDynamicSqlSupport.rankList)
                .where(RankListDynamicSqlSupport.songListId, isEqualTo(songList.getId()))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        int sum = rankMapper.selectOne(selectSumScore)
                .map(RankList::getScore)
                .orElse(0);

        if(sum == 0){
            return sum;
        }else{
            long count = rankMapper.count(c -> c.where(
                    RankListDynamicSqlSupport.songListId, isEqualTo(songList.getId())));
            return (double) sum / count;
        }
    }


    @Override
    public void save(SongListAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()) {
            case NEW -> insertAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            case UPDATED -> updateAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("未实现");
        }
    }

    private void insertAggregate(SongListAggregate aggregateRoot) {
        SongList record = converter.convertFrom(aggregateRoot);
        songListMapper.insert(record);
    }


    private void deleteAggregate(SongListAggregate aggregateRoot) {
        Long id_ = aggregateRoot.getId().value();

        songListMapper.delete(c -> c.where(id, isEqualTo(id_)));
        // 删除歌单关联中的所有绑定歌曲的关系
        songListMapper.delete(c ->
                c.where(ListSongDynamicSqlSupport.songListId, isEqualTo(aggregateRoot.getId().value())));
    }


    /**
     * 因为增加歌单中的歌曲也算是对歌单的更新。
     */
    private void updateAggregate(SongListAggregate aggregateRoot) {
        SongList record = converter.convertFrom(aggregateRoot);
        songListMapper.updateByPrimaryKeySelective(record);
        // 处理歌曲
        updateSongListForBindSong(aggregateRoot);
        // 评论
        updateSongListForComments(aggregateRoot);
    }

    /**
     * 处理歌单更新中的评论
     */
    private void updateSongListForComments(SongListAggregate aggregateRoot) {
        Map<ChangingStatus, List<CommentEntity>> commentCollect = aggregateRoot.getComments().stream()
                .collect(Collectors.groupingBy(CommentEntity::getChangingStatus));

        if (commentCollect.containsKey(ChangingStatus.NEW)) {
            List<Comment> comments = converter.toCommentDataModel(commentCollect.get(ChangingStatus.NEW), aggregateRoot.getId().value());
            if (!comments.isEmpty()) {
                commentMapper.insertMultiple(comments);
            }
        } else if (commentCollect.containsKey(ChangingStatus.DELETED)) {
            List<Long> ids = commentCollect.get(ChangingStatus.DELETED).stream()
                    .map(CommentEntity::getId)
                    .toList();
            if (!ids.isEmpty()) {
                commentMapper.delete(c -> c.where(CommentDynamicSqlSupport.id, isIn(ids)));
            }
        } else if (commentCollect.containsKey(ChangingStatus.UPDATED)) {
            // 用户更新在某个歌单同时更新关于自己评论的好几条，这里采用for来处理单一的情况
            for (CommentEntity commentEntity : commentCollect.get(ChangingStatus.UPDATED)) {
                commentMapper.update(c -> c.set(CommentDynamicSqlSupport.content)
                        .equalTo(commentEntity.getContent())
                        .where(CommentDynamicSqlSupport.id, isEqualTo(commentEntity.getId())));
            }
        }
    }

    /**
     * 处理歌单更新中的歌曲
     */
    private void updateSongListForBindSong(SongListAggregate aggregateRoot) {
        List<BindSongValueObj> bindSongs = aggregateRoot.getBindSongs();

        Map<ChangingStatus, List<BindSongValueObj>> songCollect = bindSongs.stream()
                .collect(Collectors.groupingBy(BindSongValueObj::getChangingStatus));

        if (songCollect.containsKey(ChangingStatus.NEW)) {
            List<ListSong> listSongRecords = converter.toListSongDataModel(songCollect.get(ChangingStatus.NEW));
            if (!listSongRecords.isEmpty()) {
                listSongMapper.insertMultiple(listSongRecords);
            }
        } else if (songCollect.containsKey(ChangingStatus.DELETED)) {
            List<Long> ids = converter.getSongId(songCollect.get(ChangingStatus.DELETED));
            // https://mybatis.org/mybatis-dynamic-sql/docs/configuration.html
            songListMapper.delete(c ->
                    c.where(ListSongDynamicSqlSupport.songListId, isEqualTo(aggregateRoot.getId().value()))
                            .and(ListSongDynamicSqlSupport.songId, isIn(ids))
                            .configureStatement(stmt -> stmt.setNonRenderingWhereClauseAllowed(true)));
        }
    }
}
