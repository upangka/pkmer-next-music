package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.ddd.common.ChangingStatus;
import io.gitee.pkmer.music.domain.songlist.BindSongValueObj;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.id;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Repository
public class SongListRepositoryImpl implements SongListRepository {
    private final SongListDynamicMapper songListMapper;
    private final ListSongDynamicMapper listSongMapper;
    private final SongListConverter converter;

    public SongListRepositoryImpl(SongListDynamicMapper songListDynamicMapper,
                                  ListSongDynamicMapper listSongDynamicMapper,
                                  SongListConverter converter) {
        this.songListMapper = songListDynamicMapper;
        this.listSongMapper = listSongDynamicMapper;
        this.converter = converter;
    }

    @Override
    public SongListAggregate load(SongListId songListId) {
        Optional<SongList> optionalSongList = songListMapper.selectOne(
                c -> c.where(id, isEqualTo(songListId.value())));
        if (optionalSongList.isPresent()) {
            SongList songList = optionalSongList.get();

            List<ListSong> songIds = listSongMapper.select(c ->
                    c.where(id, isEqualTo(songList.getId())));

            return converter.buildAggregate(songList,songIds);
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
        List<ListSong> listSongRecords = handleListSongDataModel(aggregateRoot.getSongIds());

        songListMapper.insert(record);
        if (!listSongRecords.isEmpty()) {
            listSongMapper.insertMultiple(listSongRecords);
        }
    }



    private void deleteAggregate(SongListAggregate aggregateRoot) {
        Long id_ = aggregateRoot.getId().value();

        List<Long> ids = converter.getSongId(aggregateRoot.getSongIds());
        songListMapper.delete(c -> c.where(id,isEqualTo(id_)));
        // https://mybatis.org/mybatis-dynamic-sql/docs/configuration.html
        songListMapper.delete(c ->
                c.where(ListSongDynamicSqlSupport.songListId,isEqualTo(aggregateRoot.getId().value()))
                 .and(ListSongDynamicSqlSupport.songId,isIn(ids))
                 .configureStatement(stmt -> stmt.setNonRenderingWhereClauseAllowed(true)));
    }




    private void updateAggregate(SongListAggregate aggregateRoot) {
        SongList record = converter.convertFrom(aggregateRoot);
        songListMapper.updateByPrimaryKeySelective(record);
        // 不处理绑定关系，因为这里是歌单，所以不处理绑定关系
    }


    private List<ListSong> handleListSongDataModel(List<BindSongValueObj> songIds) {
        List<ListSong> listSongRecords = new ArrayList<>();
        for (BindSongValueObj vb : songIds) {
            if (vb.getChangingStatus().equals(ChangingStatus.NEW)) {
                var listSong = converter.toListSongDataModel(vb);
                listSongRecords.add(listSong);
            }
        }
        return listSongRecords;
    }

}
