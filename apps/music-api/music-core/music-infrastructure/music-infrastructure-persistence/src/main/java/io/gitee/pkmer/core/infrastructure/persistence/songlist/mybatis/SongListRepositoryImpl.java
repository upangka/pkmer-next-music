package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.music.domain.song.*;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.*;
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
    public SongListRepositoryImpl(SongListDynamicMapper songListDynamicMapper,SongListBuilderFactory songListBuilderFactory){
        this.songListMapper = songListDynamicMapper;
        this.songListBuilderFactory = songListBuilderFactory;
    }

    @Override
    public SongListAggregate load(SongListId songListId) {
        Optional<SongList> optionalSongList = songListMapper.selectOne(
                c -> c.where(id,isEqualTo(songListId.value())));
        if(optionalSongList.isPresent()){
            SongList songList = optionalSongList.get();
            SongListAggregate aggregate = songListBuilderFactory.createSongListBuilder()
                    .id(songList.getId())
                    .pic(songList.getPic())
                    .title(songList.getTitle())
                    .styles(songList.getStyle())
                    .introduction(songList.getIntroduction())
                    .build();
            // todo 歌单绑定的歌曲
            return aggregate;
        }else{
            return null;
        }
    }

    @Override
    public void save(SongListAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()){
            case NEW -> insertAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("未实现");
        }
    }


    private void insertAggregate(SongListAggregate aggregateRoot){
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

    private void deleteAggregate(SongListAggregate aggregateRoot){
        Long id_ = aggregateRoot.getId().value();
        DeleteStatementProvider deleteProvider = deleteFrom(songList)
                .where(id, isEqualTo(id_))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        songListMapper.delete(deleteProvider);
//       // songListMapper.delete(c -> c.where(id,isEqualTo(id_)));
        // todo 关联的歌曲删除
    }
}
