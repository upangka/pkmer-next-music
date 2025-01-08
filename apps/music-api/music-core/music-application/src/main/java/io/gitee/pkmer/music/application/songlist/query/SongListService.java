package io.gitee.pkmer.music.application.songlist.query;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongList;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicMapper;
import io.gitee.pkmer.music.domain.song.Style;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.style;
import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.title;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.where;

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
    public SongListService(SongListDynamicMapper songListDynamicMapper){
        this.songListMapper = songListDynamicMapper;
    }

    /**
     * 分页查询
     * @param query
     */
    public PageResponse<SongListView> pageQuery(SongListPageQuery query){

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


        int total = (int) songListMapper.count(c -> c);

        // todo 歌单绑定的歌曲
        List<SongListView> list = songLists.stream().map(this::buildView).toList();

        PageResponse<SongListView> pageResponse = new PageResponse<>();
        pageResponse.setList(list)
                .setTotal(total)
                .setTotalPages(total / query.getPageSize() + 1)
                .setCurrentPageNo(query.getPageNo());
        return pageResponse;
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
                .pic(songList.getPic())
                .introduction(songList.getIntroduction())
                .build();
    }
}
