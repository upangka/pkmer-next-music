package io.gitee.pkmer.music.application.songlist.query;

import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.Singer;
import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.id;
import static io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicSqlSupport.singer;
import static io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.SqlBuilder.where;

@Component
public class GetSongListDetailHandler implements CommandHandler<GetSongListDetailCmd, SongListDetailView> {
    private final SongListRepository songListrepository;
    private final SongDynamicMapper songDynamicMapper;
    private final SingerDynamicMapper singerDynamicMapper;
    private final String minioServer;

    public GetSongListDetailHandler(SongListRepository songListrepository,
                                    SongDynamicMapper songDynamicMapper,
                                    SingerDynamicMapper singerDynamicMapper,
                                    PkmerMinioProps props) {
        this.songListrepository = songListrepository;
        this.songDynamicMapper = songDynamicMapper;
        this.minioServer = props.getUrl();
        this.singerDynamicMapper = singerDynamicMapper;
    }

    @Override
    public SongListDetailView execute(GetSongListDetailCmd cmd) {
        SongListAggregate songList = songListrepository.load(new SongListId(cmd.getSongListId())).orElseThrow(
                () -> new RuntimeException("歌单不存在")
        );
        Map<Long, SongListDetailView.SongView> songs = getSongs(songList);

        return buildSongDetailView(songList,songs);
    }

    private Map<Long, SongListDetailView.SongView> getSongs(SongListAggregate songList) {
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

        List<Song> songs = songDynamicMapper.selectMany(selectProvider);
        Map<Long, String> singers;
        if(!songs.isEmpty()){
            // 将返回的结果合并到singers map
            singers = getSingerName(songs);
        } else {
            singers = new HashMap<>();
        }


        return songs.stream()
                .map(song -> toSongView(song,singers))
                .collect(Collectors.toMap(
                        song -> Long.parseLong(song.getId()),
                        song -> song
                ));
    }

    /**
     * 根据歌曲列表获取对应的歌手名称
     * 该方法通过歌手ID查询歌手表，以获取每个歌手的名称，并以Map形式返回，键为歌手ID，值为歌手名称
     *
     * @param songs 歌曲列表，用于提取歌手ID
     * @return 返回一个Map，键为歌手ID，值为歌手名称
     */
    private Map<Long, String> getSingerName(List<Song> songs){
        List<Long> singerId = songs.stream().map(Song::getSingerId).toList();

        SelectStatementProvider selectProvider = select(id, name)
                .from(singer)
                .where(id, isIn(singerId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

       return singerDynamicMapper.selectMany(selectProvider)
               .stream()
               .collect(Collectors.toMap(
                       Singer::getId,
                       Singer::getName
               ));
    }

    private SongListDetailView.SongView toSongView(Song song, final Map<Long,String> singers) {
        return SongListDetailView.SongView.builder()
                .id(song.getId().toString())
                .singerId(song.getSingerId().toString())
                .singerName(singers.get(song.getSingerId()))
                .name(song.getName())
                .introduction(song.getIntroduction())
                .pic(addMinioServer(song.getPic()))
                .url(addMinioServer(song.getUrl()))
                .build();
    }

    private SongListDetailView buildSongDetailView(SongListAggregate songList, Map<Long, SongListDetailView.SongView> songs){
        List<String> styles = songList.getStyles().stream().map(Style::getDesc).toList();

        List<SongListDetailView.SongView> songViews = new ArrayList<>();


        songList.getSongIds().forEach(songId -> {
            Long id = songId.getSongId().value();
            SongListDetailView.SongView song = songs.get(id);
            if(song != null){
                songViews.add(song);
            }
        });

        return SongListDetailView.builder()
                .id(songList.getId().toString())
                .styles(styles)
                .title(songList.getTitle())
                .pic(addMinioServer(songList.getPic()))
                .introduction(songList.getIntroduction())
                .score(songList.getScore())
                .songs(songViews)
                .build();
    }

    private String addMinioServer(String src){
        return minioServer + src;
    }

}
