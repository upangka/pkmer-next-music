package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.music.domain.song.SongListAggregate;
import io.gitee.pkmer.music.domain.song.SongListBuilder;
import io.gitee.pkmer.music.domain.song.SongListId;
import io.gitee.pkmer.music.domain.song.SongListRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public SongListRepositoryImpl(SongListDynamicMapper songListDynamicMapper){
        this.songListMapper = songListDynamicMapper;
    }

    @Override
    public SongListAggregate load(SongListId songListId) {
        Optional<SongList> optionalSongList = songListMapper.selectOne(
                c -> c.where(id,isEqualTo(songListId.value())));
        if(optionalSongList.isPresent()){
            SongList songList = optionalSongList.get();
            SongListAggregate aggregate = SongListBuilder.builder()
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
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("未实现");
        }
    }

    private void deleteAggregate(SongListAggregate aggregateRoot){
        Long id_ = aggregateRoot.getId().value();
        songListMapper.delete(c -> c.where(id,isEqualTo(id_)));
        // todo 关联的歌曲删除
    }
}
