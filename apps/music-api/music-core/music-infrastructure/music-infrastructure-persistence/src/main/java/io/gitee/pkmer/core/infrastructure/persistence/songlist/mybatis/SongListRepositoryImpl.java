package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.music.domain.song.SongListAggregate;
import io.gitee.pkmer.music.domain.song.SongListId;
import io.gitee.pkmer.music.domain.song.SongListRepository;
import io.gitee.pkmer.music.domain.song.page.SongListPage;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    private final SongListConverter converter;
    public SongListRepositoryImpl(SongListDynamicMapper songListDynamicMapper,SongListConverter converter) {
        this.songListMapper = songListDynamicMapper;
        this.converter = converter;
    }

    @Override
    public SongListAggregate load(SongListId songListId) {
        Optional<SongList> optionalSongList = songListMapper.selectOne(
                c -> c.where(id, isEqualTo(songListId.value())));
        if (optionalSongList.isPresent()) {
            SongList songList = optionalSongList.get();
            SongListAggregate aggregate = converter.buildAggregate(songList);
            // todo 歌单绑定的歌曲
            return aggregate;
        } else {
            return null;
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
        // https://mybatis.org/mybatis-dynamic-sql/docs/conditions.html#value-transformation
        WhereApplier whereApplier =
                where(style, isLikeWhenPresent(songListPage.getStyle()).map(s -> "%"+s+"%"))
                        .and(title, isLikeWhenPresent(songListPage.getTitle()).map(s -> "%"+s+"%"))
                        .toWhereApplier();

        // todo 优化分页抽象类，直接offset
        List<SongList> songLists = songListMapper.select(c ->
                c.applyWhere(whereApplier)
                        .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
                        .limit(songListPage.getPageSize())
                        .offset(songListPage.offset()));


        int total = (int) songListMapper.count(c -> c);

        // todo 歌单绑定的歌曲
        List<SongListAggregate> list = songLists.stream().map(converter::buildAggregate).toList();

        PageResponse<SongListAggregate> pageResponse = new PageResponse<>();
        pageResponse.setList(list)
                .setTotal(total)
                .setTotalPages(total / songListPage.getPageSize() + 1)
                .setCurrentPageNo(songListPage.getPageNo());
        return pageResponse;
    }


    private void updateAggregate(SongListAggregate aggregateRoot){
        SongList record = converter.convertFrom(aggregateRoot);
        songListMapper.updateByPrimaryKeySelective(record);

        // todo 歌曲与歌单的关联
    }



}
