package io.gitee.pkmer.core.infrastructure.persistence.song;

import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.song.SongRepository;
import org.springframework.stereotype.Repository;

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

    public SongRepositoryImpl(SongDynamicMapper songDynamicMapper) {
        this.songDynamicMapper = songDynamicMapper;
    }

    @Override
    public SongAggregate load(SongId songId) {

        Song row = null;
        songDynamicMapper.insert();
        return null;
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

    }

    private void updateAggregate(SongAggregate aggregateRoot){

    }

    private void deleteAggregate(SongAggregate aggregateRoot){

    }
}
