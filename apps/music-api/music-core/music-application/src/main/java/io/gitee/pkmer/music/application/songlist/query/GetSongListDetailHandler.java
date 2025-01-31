package io.gitee.pkmer.music.application.songlist.query;

import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicMapper;
import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.songlist.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.id;
import static io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.SqlBuilder.where;

@Component
public class GetSongListDetailHandler implements CommandHandler<GetSongListDetailCmd, SongDetailView> {
    private final SongListRepository songListrepository;
    private final SongDynamicMapper songDynamicMapper;
    private final String minioServer;

    public GetSongListDetailHandler(SongListRepository songListrepository,
                                    SongDynamicMapper songDynamicMapper,
                                    PkmerMinioProps props) {
        this.songListrepository = songListrepository;
        this.songDynamicMapper = songDynamicMapper;
        this.minioServer = props.getUrl();
    }

    @Override
    public SongDetailView execute(GetSongListDetailCmd cmd) {
        SongListAggregate songList = songListrepository.load(new SongListId(cmd.getSongListId())).orElseThrow(
                () -> new RuntimeException("歌单不存在")
        );
        Map<Long, SongDetailView.SongView> songs = getSongs(songList);
        return buildSongDetailView(songList,songs);
    }

    private Map<Long, SongDetailView.SongView> getSongs(SongListAggregate songList) {
        List<Long> songIds = songList.getBindSongs().stream().map(s -> s.getSongId().value()).toList();

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

        return songDynamicMapper.selectMany(selectProvider).stream()
                .map(this::toSongView)
                .collect(Collectors.toMap(
                        song -> Long.parseLong(song.getId()),
                        song -> song
                ));
    }

    private SongDetailView.SongView toSongView(Song song) {
        return SongDetailView.SongView.builder()
                .id(song.getId().toString())
                .singerId(song.getSingerId().toString())
                .name(song.getName())
                .introduction(song.getIntroduction())
                .pic(addMinioServer(song.getPic()))
                .url(addMinioServer(song.getUrl()))
                .build();
    }

    private SongDetailView buildSongDetailView(SongListAggregate songList,Map<Long,SongDetailView.SongView> songs){
        List<String> styles = songList.getStyles().stream().map(Style::getDesc).toList();

        List<SongDetailView.SongView> songViews = new ArrayList<>();


        songList.getSongIds().forEach(songId -> {
            Long id = songId.getSongId().value();
            SongDetailView.SongView song = songs.get(id);
            if(song != null){
                songViews.add(song);
            }
        });

        return SongDetailView.builder()
                .id(songList.getId().toString())
                .styles(styles)
                .title(songList.getTitle())
                .pic(addMinioServer(songList.getPic()))
                .introduction(songList.getIntroduction())
                .songs(songViews)
                .build();
    }

    private String addMinioServer(String src){
        return minioServer + src;
    }

}
