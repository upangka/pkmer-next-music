package io.gitee.pkmer.music.application.song;

import io.gitee.common.util.TotalPagesHelper;
import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.Singer;
import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.application.song.dto.SongDto;
import io.gitee.pkmer.music.application.song.get.SongDetailView;
import io.gitee.pkmer.music.application.song.query.SongQuery;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.id;
import static io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicSqlSupport.singer;
import static io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport.*;
import static io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport.song;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@RequiredArgsConstructor
@Service
public class SongService {
    private final SongDynamicMapper songDynamicMapper;
    private final SingerDynamicMapper singerDynamicMapper;
    private final PkmerMinioProps minioProps;

    private List<Song> getSongs(List<Long> songIds){
        WhereApplier whereApplier = where().toWhereApplier();
        if(!songIds.isEmpty()){
            whereApplier = where(id, isIn(songIds)).toWhereApplier();
        }
        SelectStatementProvider selectProvider = select(id, singerId, introduction, name, pic, url)
                .from(song)
                .applyWhere(whereApplier)
                .configureStatement(c -> c.setNonRenderingWhereClauseAllowed(true))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return songDynamicMapper.selectMany(selectProvider);
    }

    /**
     * 根据singerid获取歌手的名字
     * @param singerIds
     * @return
     */
    private Map<Long,String> getSingerNames(List<Long> singerIds){
        SelectStatementProvider selectProvider = select(id, name)
                .from(singer)
                .where(id, isIn(singerIds))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return singerDynamicMapper.selectMany(selectProvider)
                .stream()
                .collect(Collectors.toMap(
                        Singer::getId,
                        Singer::getName
                ));
    }


    /**
     * 获取带有歌手名字的歌曲
     * @param songIds
     * @return
     */
    public List<SongDto> getSongwithSingerName(List<Long> songIds){
        List<Song> songs = getSongs(songIds);
        List<Long> singerIds = songs.stream().map(Song::getSingerId).toList();
        Map<Long, String> singerNames = getSingerNames(singerIds);

        List<SongDto> results = new ArrayList<>();

        for (Song song : songs){
            SongDto dto = Converter.Instance.toDto(song);

            dto.setSingerName(singerNames.get(song.getSingerId()));
            dto.setPic(addMinioServer(song.getPic()));
            dto.setUrl(addMinioServer(song.getUrl()));

            results.add(dto);
        }
        return results;

    }

    private String addMinioServer(String url){
        if(url == null || url.isBlank()){
            return "";
        }
        return minioProps.getUrl() + url;
    }


    /**
     * 分页查询歌曲详情
     * @param query
     * @return
     */
    public PageResponse<SongDetailView> pageQuerySongDetail(SongQuery query){

        WhereApplier whereApplier = buildPageQuerySongWhereApplier(query);
        SelectStatementProvider selectProvider = select(song.allColumns())
                .from(song)
                .applyWhere(whereApplier)
                .orderBy(song.createTime)
                .limit(query.getPageSize())
                .offset(query.offset())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        List<Song> songs = songDynamicMapper.selectMany(selectProvider);
        List<SongDetailView> results = new ArrayList<>();

        for (Song song : songs){
            // 不传递歌词和歌曲链接
            SongDetailView view = SongDetailView.builder()
                    .id(song.getId().toString())
                    .singerId(song.getSingerId().toString())
                    .pic(addMinioServer(song.getPic()))
                    .name(song.getName())
                    .introduction(song.getIntroduction())
                    .build();
            results.add(view);
        }

        TotalView total = getPageQueryTotal(query);


        return PageResponse.<SongDetailView>builder()
                .list(results)
                .total(total.getTotal())
                .totalPages(total.getTotalPages())
                .build();
    }


    /**
     * 获取歌曲分页的总页数
     * @param query
     * @return
     */
    public TotalView getPageQueryTotal(SongQuery query){
        WhereApplier whereApplier = buildPageQuerySongWhereApplier(query);

        SelectStatementProvider selectProvider = select(count())
                .from(song)
                .applyWhere(whereApplier)
                .build()
                .render(RenderingStrategies.MYBATIS3);


        int total = (int)songDynamicMapper.count(selectProvider);
        int totalPages = TotalPagesHelper.calcTotalPages(total, query.getPageSize());
        return TotalView.builder()
                .total(total)
                .totalPages(totalPages)
                .build();
    }


    private WhereApplier buildPageQuerySongWhereApplier(SongQuery query){
        return where(song.singerId, isEqualTo(query.getSingerId()))
                .and(song.name, isLikeWhenPresent(query.getName()).map(s -> "%" + s + "%"))
                .toWhereApplier();
    }

    @Mapper
    public interface Converter{
        Converter Instance = Mappers.getMapper(Converter.class);

        @Mapping(target = "singerName", ignore = true) // 忽略自动映射
        SongDto  toDto(Song song);

        List<SongDto> toDtos(List<Song> songs);
    }

}
