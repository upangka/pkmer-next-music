package io.gitee.pkmer.music.application.songlist.query;

import io.gitee.pkmer.convention.converter.TargetAndSourceConverter;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.Comment;
import io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.CommentDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.CommentDynamicSqlSupport;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongList;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicMapper;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.songlist.Style;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.style;
import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.title;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * CQRS分离，抽离出查询
 * <p>
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/8
 * </p>
 */
@Service
public class SongListService {
    private final SongListDynamicMapper songListMapper;
    private final CommentDynamicMapper commentMapper;
    private final String minioServer;

    public SongListService(SongListDynamicMapper songListMapper,
                           CommentDynamicMapper commentMapper,
                           PkmerMinioProps props){
        this.songListMapper = songListMapper;
        this.commentMapper = commentMapper;
        minioServer = props.getUrl();
    }
    /**
     * 分页查询歌单
     * @param query
     */
    public PageResponse<SongListView> pageQuerySongList(SongListPageQuery query){

        // https://mybatis.org/mybatis-dynamic-sql/docs/conditions.html#value-transformation
        WhereApplier whereApplier =
                where(style, isLikeWhenPresent(query.getStyle()).map(s -> "%"+s+"%"))
                        .and(title, isLikeWhenPresent(query.getTitle()).map(s -> "%"+s+"%"))
                        .toWhereApplier();

        List<SongList> songLists = songListMapper.select(c ->
                c.applyWhere(whereApplier)
                        .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
                        .limit(query.getPageSize())
                        .offset(query.offset()));


        int total = (int) songListMapper.count(c -> c.applyWhere(whereApplier));

        List<SongListView> list = songLists.stream().map(this::buildView).toList();

        PageResponse<SongListView> pageResponse = new PageResponse<>();
        pageResponse.setList(list)
                .setTotal(total)
                .setTotalPages(total / query.getPageSize() + 1)
                .setCurrentPageNo(query.getPageNo());
        return pageResponse;
    }

    /**
     * 分页查询歌单的评论
     * @param query
     */
    public PageResponse<SongListCommentsView> pageQueryComments(SongListCommentQuery query){

        WhereApplier whereApplier = where(CommentDynamicSqlSupport.songListId, isEqualTo(query.getSongListId())).toWhereApplier();

        List<Comment> comments = commentMapper.select(c -> c
                .applyWhere(whereApplier)
                .limit(query.getPageSize())
                .offset(query.offset()));

        long total = commentMapper.count(c -> c.applyWhere(whereApplier));

        List<SongListCommentsView> list = toTargets(comments);

        PageResponse<SongListCommentsView> pageResponse = new PageResponse<>();
        pageResponse.setList(list)
                .setTotal((int)total)
                .setTotalPages((int)total / query.getPageSize() + 1)
                .setCurrentPageNo(query.getPageNo());
        return pageResponse;
    }



    public SongListCommentsView toTarget(Comment source) {
        if ( source == null ) {
            return null;
        }

        SongListCommentsView songListCommentsView = new SongListCommentsView();

        songListCommentsView.setId( source.getId() );
        songListCommentsView.setUserId( source.getUserId() );
        songListCommentsView.setContent( source.getContent() );
        songListCommentsView.setCreateTime( source.getCreateTime() );
        songListCommentsView.setUp( source.getUp() );

        return songListCommentsView;
    }

    public List<SongListCommentsView> toTargets(List<Comment> sources) {
        if ( sources == null ) {
            return null;
        }

        List<SongListCommentsView> list = new ArrayList<SongListCommentsView>( sources.size() );
        for ( Comment comment : sources ) {
            list.add( toTarget( comment ) );
        }

        return list;
    }


    /**
     * 转换数据模型为视图
     * @param songList
     * @return
     */
    private SongListView buildView(SongList songList){
        List<String> styles = Style.toStyles(songList.getStyle()).stream().map(Style::getDesc).toList();
        return SongListView.builder()
                .id(songList.getId().toString())
                .styles(styles)
                .title(songList.getTitle())
                .pic(addMinioServerToPic(songList.getPic()))
                .introduction(songList.getIntroduction())
                .build();
    }


    private String addMinioServerToPic(String pic){
        return minioServer + pic;
    }
}
