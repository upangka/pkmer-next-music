package io.gitee.pkmer.core.infrastructure.persistence.song;

import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.song.SongRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
@Repository
public class SongRepositoryImpl implements SongRepository {

    private final SongDynamicMapper songDynamicMapper;
    private final SongConverter converter;

    public SongRepositoryImpl(SongDynamicMapper songDynamicMapper,
                              SongConverter converter) {
        this.songDynamicMapper = songDynamicMapper;
        this.converter = converter;
    }

    @Override
    public SongAggregate load(SongId songId) {
        Optional<Song> song = songDynamicMapper.selectByPrimaryKey(songId.value());
        return song.map(converter::covertTo).orElse(null);
    }

    @Override
    public void save(SongAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()){
            case NEW -> insertAggregate(aggregateRoot);
            case UPDATED -> updateAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("不支持的操作");
        }
    }

    private void insertAggregate(SongAggregate aggregateRoot){
        Song song = converter.covertFrom(aggregateRoot);
        songDynamicMapper.insert(song);
    }

    private void updateAggregate(SongAggregate aggregateRoot){
        Song song = converter.covertFrom(aggregateRoot);
        songDynamicMapper.updateByPrimaryKeySelective(song);
    }

    private void deleteAggregate(SongAggregate aggregateRoot){
        songDynamicMapper.deleteByPrimaryKey(aggregateRoot.getId().value());
    }
}
