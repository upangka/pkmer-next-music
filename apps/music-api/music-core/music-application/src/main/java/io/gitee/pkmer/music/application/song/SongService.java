package io.gitee.pkmer.music.application.song;

import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.Singer;
import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.application.song.dto.SongDto;
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


    public List<SongDto> getSongwithSingerName(List<Long> songIds){
        List<Song> songs = getSongs(songIds);
        List<Long> singerIds = songs.stream().map(Song::getSingerId).toList();
        Map<Long, String> singerNames = getSingerNames(singerIds);

        List<SongDto> results = new ArrayList<>();

        for (Song song : songs){
            SongDto dto = Converter.Instance.toDto(song);

            dto.setSingerName(singerNames.get(song.getSingerId()));
            dto.setPic(minioProps.getUrl() + song.getPic());
            dto.setUrl(minioProps.getUrl() + song.getUrl());

            results.add(dto);
        }
        return results;

    }

    @Mapper
    public interface Converter{
        Converter Instance = Mappers.getMapper(Converter.class);

        @Mapping(target = "singerName", ignore = true) // 忽略自动映射
        SongDto  toDto(Song song);

        List<SongDto> toDtos(List<Song> songs);
    }

}
