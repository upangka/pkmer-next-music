package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.music.domain.song.*;
import io.gitee.pkmer.music.domain.song.page.SongListPage;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Repository
public class SongListRepositoryImpl implements SongListRepository {
    private final SongListDynamicMapper songListMapper;
    private final SongListBuilderFactory songListBuilderFactory;

    public SongListRepositoryImpl(SongListDynamicMapper songListDynamicMapper, SongListBuilderFactory songListBuilderFactory) {
        this.songListMapper = songListDynamicMapper;
        this.songListBuilderFactory = songListBuilderFactory;
    }

    @Override
    public SongListAggregate load(SongListId songListId) {
        Optional<SongList> optionalSongList = songListMapper.selectOne(
                c -> c.where(id, isEqualTo(songListId.value())));
        if (optionalSongList.isPresent()) {
            SongList songList = optionalSongList.get();
            SongListAggregate aggregate = buildAggregate(songList);
            // todo 歌单绑定的歌曲
            return aggregate;
        } else {
            return null;
        }
    }

    private SongListAggregate buildAggregate(SongList songList) {
        // todo 绑定歌单对应的歌曲
        return songListBuilderFactory.createSongListBuilder()
                .id(songList.getId())
                .pic(songList.getPic())
                .title(songList.getTitle())
                .styles(songList.getStyle())
                .introduction(songList.getIntroduction())
                .build();
    }

    @Override
    public void save(SongListAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()) {
            case NEW -> insertAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("未实现");
        }
    }

    private void insertAggregate(SongListAggregate aggregateRoot) {
        SongList record = new SongList();
        List<Style> styles = aggregateRoot.getStyles();
        String style = styles.stream().map(Style::getDesc)
                .collect(Collectors.joining("-"));

        record.setTitle(aggregateRoot.getTitle());
        record.setPic(aggregateRoot.getPic());
        record.setStyle(style);
        record.setIntroduction(aggregateRoot.getIntroduction());

        // todo 歌曲与歌单的关联
        songListMapper.insert(record);
    }

    private void deleteAggregate(SongListAggregate aggregateRoot) {
        Long id_ = aggregateRoot.getId().value();
        DeleteStatementProvider deleteProvider = deleteFrom(songList)
                .where(id, isEqualTo(id_))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        songListMapper.delete(deleteProvider);
//       // songListMapper.delete(c -> c.where(id,isEqualTo(id_)));
        // todo 关联的歌曲删除
    }

    @Override
    public PageResponse<SongListAggregate> pageLoad(SongListPage songListPage) {
        WhereApplier whereApplier =
                where(style, isEqualToWhenPresent(songListPage.getStyle()))
                        .toWhereApplier();

        // todo 优化分页抽象类，直接offset
        List<SongList> songLists = songListMapper.select(c ->
                c.applyWhere(whereApplier)
                        .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
                        .limit(songListPage.getPageSize())
                        .offset(songListPage.offset()));


        int total = (int) songListMapper.count(c -> c);

        // todo 歌单绑定的歌曲
        List<SongListAggregate> list = songLists.stream().map(this::buildAggregate).toList();

        PageResponse<SongListAggregate> pageResponse = new PageResponse<>();
        pageResponse.setList(list)
                .setTotal(total)
                .setTotalPages(total / songListPage.getPageSize() + 1)
                .setCurrentPageNo(songListPage.getPageNo());
        return pageResponse;
    }
}
