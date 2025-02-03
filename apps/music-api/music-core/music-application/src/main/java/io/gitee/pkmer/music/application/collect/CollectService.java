package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.Collect;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicSqlSupport;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport;
import io.gitee.pkmer.music.application.song.SongService;
import io.gitee.pkmer.music.application.song.dto.SongDto;
import io.gitee.pkmer.music.domain.enums.SongAndListType;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicSqlSupport.*;
/**
 * DDD CQSR 查询分离
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@RequiredArgsConstructor
@Service
public class CollectService {

    private final CollectDynamicMapper collectDynamicMapper;
    private final SongDynamicMapper songDynamicMapper;
    private final SongService songService;
    public PageResponse<CollectView> pageQuery(CollectQuery query) {

        WhereApplier whereApplier = where(CollectDynamicSqlSupport.userId, isEqualTo(query.getUserId())).toWhereApplier();

        List<Collect> collects = collectDynamicMapper.select(c -> c.applyWhere(whereApplier)
                .orderBy(CollectDynamicSqlSupport.createTime));

        long total = collectDynamicMapper.count(c -> c.applyWhere(whereApplier));

        List<CollectView> views = toViews(collects);

        return PageResponse.<CollectView>builder()
                .total((int) total)
                .totalPages((int) total / query.getPageSize() + 1)
                .currentPageNo(query.getPageNo())
                .list(views)
                .build();

    }


    private CollectView.SongView toSongView(SongDto dto){
        return CollectView.SongView.builder()
                .songId(dto.getId().toString())
                .name(dto.getName())
                .singerId(dto.getSingerId().toString())
                .singerName(dto.getSingerName())
                .link(dto.getUrl())
                .picture(dto.getPic())
                .introduction(dto.getIntroduction())
                .build();
    }


    /**
     * 判断用户是否收藏了歌曲
     *
     * @param userId 用户id
     * @param songId 歌曲id
     * @return true 收藏 false 未收藏
     */
    public boolean isCollectSong(Long userId, Long songId) {
        return getCollectBySongId(userId, songId).isPresent();
    }


    public Optional<CollectView> getCollectBySongId(Long userId, Long songId){
        WhereApplier whereApplier = where(CollectDynamicSqlSupport.userId, isEqualTo(userId))
                .and(CollectDynamicSqlSupport.songId, isEqualTo(songId)).toWhereApplier();
       return getCollectView(whereApplier);
    }

    /**
     * 根据歌曲id查询歌单收藏
     * @param userId
     * @param songListId
     * @return
     */

    public Optional<CollectView> getCollectBySongListId(Long userId, Long songListId){
        WhereApplier whereApplier = where(CollectDynamicSqlSupport.userId, isEqualTo(userId))
                .and(CollectDynamicSqlSupport.songListId, isEqualTo(songListId)).toWhereApplier();
        return getCollectView(whereApplier);
    }

    private Optional<CollectView> getCollectView(WhereApplier whereApplier){

        SelectStatementProvider selectProvider = select(collect.allColumns())
                .from(collect)
                .applyWhere(whereApplier)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        System.out.println(selectProvider.getSelectStatement());
        Optional<Collect> collectOptional = collectDynamicMapper.selectOne(selectProvider);

        if(collectOptional.isEmpty()){
            return Optional.empty();
        }else{
            Collect collect = collectOptional.get();
            return Optional.of(toView(collect,null));
        }
    }


    private CollectView toView(Collect collect, CollectView.SongView song) {
        String type = SongAndListType.valueOf(collect.getType()).getDesc();
        return CollectView.builder()
                .id(collect.getId())
                .songListId(collect.getSongListId())
                .type(type)
                .song(song)
                .createTime(collect.getCreateTime())
                .build();
    }

    private List<CollectView> toViews(List<Collect> collects) {
        Map<String, CollectView.SongView> songsMap = getStringSongViewMap(collects);

        List<CollectView> results = new ArrayList<>();
        for(Collect collect : collects){
            CollectView.SongView song = songsMap.get(collect.getSongId().toString());
            CollectView view = toView(collect,song);
            results.add(view);
        }

        return results;
    }

    @NotNull
    private Map<String, CollectView.SongView> getStringSongViewMap(List<Collect> collects) {
        // 处理歌曲
        List<Long> songsId = collects.stream().map(Collect::getSongId).toList();
        Map<String, CollectView.SongView> songsMap = songService.getSongwithSingerName(songsId)
                .stream().map(this::toSongView).collect(Collectors.toMap(
                        CollectView.SongView::getSongId,
                        s -> s
                ));
        return songsMap;
    }


}

