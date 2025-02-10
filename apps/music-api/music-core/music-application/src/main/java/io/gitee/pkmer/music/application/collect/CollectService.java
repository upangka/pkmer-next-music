package io.gitee.pkmer.music.application.collect;

import io.gitee.common.util.TotalPagesHelper;
import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.*;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.Collect;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.application.song.SongService;
import io.gitee.pkmer.music.application.song.dto.SongDto;
import io.gitee.pkmer.music.domain.enums.SongAndListType;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicSqlSupport.singer;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicSqlSupport.*;
import static io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport.song;

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
    private final CollectPageMapper collectPageMapper;
    private final PkmerMinioProps pkmerMinioProps;
    private final SongService songService;


    public PageResponse<CollectSongDto> pageQueryWithSongName(CollectQuery query) {
        try {
            // 明确指定类型用于@Results,防止MyBatisSystemException null
            BasicColumn[] columns = List.of(
                    collect.id, collect.userId, collect.type, collect.createTime,
                    song.id.as("song_id"), song.name.as("song_name"), song.pic.as("song_pic"),
                    song.url.as("song_url"), song.introduction.as("song_introduction"),
                    singer.id.as("singer_id"), singer.name.as("singer_name")
            ).toArray(new BasicColumn[0]);

            SelectStatementProvider selectProvider = select(columns)
                    .from(collect)
                    .leftJoin(song)
                    .on(collect.songId, equalTo(song.id))
                    .leftJoin(singer)
                    .on(song.singerId, equalTo(singer.id))
                    .where(song.name, isLikeWhenPresent(query.getSongName()).map(s -> "%" + s + "%"))
                    .and(collect.type, isEqualTo(SongAndListType.SONG.getValue()))
                    .orderBy(collect.createTime.descending())
                    .limit(query.getPageSize())
                    .offset((long) (query.getPageNo() - 1) * query.getPageSize())
                    .build()
                    .render(RenderingStrategies.MYBATIS3);

            List<CollectSongDto> collects = collectPageMapper.selectWithLeftJoinProvider(selectProvider);

            for(CollectSongDto dto : collects){
                CollectSongDto.SongDto song = dto.getSong();
                song.setPic(pkmerMinioProps.getUrl() + song.getPic());
                song.setUrl(pkmerMinioProps.getUrl() + song.getUrl());
            }

            return PageResponse.<CollectSongDto>builder()
                    .total(collects.size()) // todo total
                    .totalPages(1)
                    .currentPageNo(query.getPageNo())
                    .list(collects)
                    .build();

        } catch (Exception r) {
            System.out.println(r.getMessage());
            throw r;
        }

    }


    /**
     * 分页查询收藏
     *
     * @param query
     * @return
     */
    public PageResponse<CollectView> pageQuery(CollectQuery query) {

        WhereApplier whereApplier = buildWhereApplier(query);

        List<Collect> collects = collectDynamicMapper.select(c -> c.applyWhere(whereApplier)
                .orderBy(CollectDynamicSqlSupport.createTime));

        int total = (int) collectDynamicMapper.count(c -> c.applyWhere(whereApplier));

        List<CollectView> views = toViews(collects);
        int totalPages = TotalPagesHelper.calcTotalPages(total, query.getPageSize());
        return PageResponse.<CollectView>builder()
                .total(total)
                .totalPages(totalPages)
                .currentPageNo(query.getPageNo())
                .list(views)
                .build();

    }


    public TotalView getPageTotal(CollectQuery query) {
        WhereApplier whereApplier = buildWhereApplier(query);
        int total = (int) collectDynamicMapper.count(c -> c.applyWhere(whereApplier));

        int totalPages = TotalPagesHelper.calcTotalPages(total, query.getPageSize());

        return TotalView.builder()
                .total(total)
                .totalPages(totalPages)
                .build();
    }

    private WhereApplier buildWhereApplier(CollectQuery query) {
        return where(CollectDynamicSqlSupport.userId, isEqualTo(query.getUserId())).toWhereApplier();
    }


    private CollectView.SongView toSongView(SongDto dto) {
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


    public Optional<CollectView> getCollectBySongId(Long userId, Long songId) {
        WhereApplier whereApplier = where(CollectDynamicSqlSupport.userId, isEqualTo(userId))
                .and(CollectDynamicSqlSupport.songId, isEqualTo(songId)).toWhereApplier();
        return getCollectView(whereApplier);
    }

    /**
     * 根据歌曲id查询歌单收藏
     *
     * @param userId
     * @param songListId
     * @return
     */

    public Optional<CollectView> getCollectBySongListId(Long userId, Long songListId) {
        WhereApplier whereApplier = where(CollectDynamicSqlSupport.userId, isEqualTo(userId))
                .and(CollectDynamicSqlSupport.songListId, isEqualTo(songListId)).toWhereApplier();
        return getCollectView(whereApplier);
    }

    private Optional<CollectView> getCollectView(WhereApplier whereApplier) {

        SelectStatementProvider selectProvider = select(collect.allColumns())
                .from(collect)
                .applyWhere(whereApplier)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        System.out.println(selectProvider.getSelectStatement());
        Optional<Collect> collectOptional = collectDynamicMapper.selectOne(selectProvider);

        if (collectOptional.isEmpty()) {
            return Optional.empty();
        } else {
            Collect collect = collectOptional.get();
            return Optional.of(toView(collect, null));
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
        for (Collect collect : collects) {
            CollectView.SongView song = songsMap.get(collect.getSongId().toString());
            CollectView view = toView(collect, song);
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

