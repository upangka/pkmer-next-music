package io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis;

import io.gitee.pkmer.core.infrastructure.persistence.singer.SingerConverter;
import io.gitee.pkmer.core.infrastructure.persistence.song.SongConverter;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport;
import io.gitee.pkmer.ddd.common.ChangingStatus;
import io.gitee.pkmer.music.domain.singer.SingerAggrerate;
import io.gitee.pkmer.music.domain.singer.SingerId;
import io.gitee.pkmer.music.domain.singer.SingerRepository;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@Repository
public class SingerRepositoryImpl implements SingerRepository {
    private final SingerDynamicMapper singerDynamicMapper;
    private final SongDynamicMapper songDynamicMapper;
    private final SingerConverter singerConverter;
    private final SongConverter songConverter;
    public SingerRepositoryImpl(SingerDynamicMapper singerDynamicMapper,
                                SongDynamicMapper songDynamicMapper,
                                SingerConverter singerConverter) {
        this.singerDynamicMapper = singerDynamicMapper;
        this.songDynamicMapper = songDynamicMapper;
        this.singerConverter = singerConverter;
        this.songConverter = singerConverter.getSongConverter();
    }

    @Override
    public SingerAggrerate load(SingerId singerId) {
        Optional<Singer> singerOptional = singerDynamicMapper.selectOne(c ->
                c.where(SingerDynamicSqlSupport.id, isEqualTo(singerId.value())));

        if (singerOptional.isPresent()) {
            Singer singer = singerOptional.get();
            List<Song> songs = songDynamicMapper.select(c ->
                    c.where(SongDynamicSqlSupport.singerId, isEqualTo(singer.getId())));

           return singerConverter.convertTo(singer, songs);
        }
        return null;
    }

    @Override
    public void save(SingerAggrerate singerRoot) {
        switch (singerRoot.getChangingStatus()){
            case NEW -> insertAggregate(singerRoot);
            case UPDATED -> updateAggregate(singerRoot);
            case DELETED -> deleteAggregate(singerRoot);
            default -> throw new UnsupportedOperationException("不支持的操作");
        }
    }

    private void insertAggregate(SingerAggrerate singerRoot){
        Singer singer = singerConverter.convertFrom(singerRoot);
        singerDynamicMapper.insert(singer);
        List<SongAggregate> songAggregates = singerRoot.getSongs();

        // 处理歌曲信息的持久化
        if(!songAggregates.isEmpty()){
            for(SongAggregate song : songAggregates){
                if(song.getChangingStatus().equals(ChangingStatus.UPDATED)){
                    songDynamicMapper.insert(songConverter.covertFrom(song));
                }
            }
        }
    }
    private void updateAggregate(SingerAggrerate singerRoot){
        Singer singer = singerConverter.convertFrom(singerRoot);
        singerDynamicMapper.updateByPrimaryKey(singer);

        List<SongAggregate> songAggregates = singerRoot.getSongs();
        // 更新歌曲信息
        if(!songAggregates.isEmpty()){
            for(SongAggregate song : songAggregates){
                if(song.getChangingStatus().equals(ChangingStatus.UPDATED)){
                    songDynamicMapper.updateByPrimaryKey(songConverter.covertFrom(song));
                }
            }
        }
    }
    private void deleteAggregate(SingerAggrerate singerRoot){
        Long singerId = singerRoot.getSingerId().value();
        singerDynamicMapper.deleteByPrimaryKey(singerId);
        // 删除歌手相关的歌曲
        songDynamicMapper.delete(c -> c.where(
                SongDynamicSqlSupport.singerId, isEqualTo(singerId)));
    }
}
