package io.gitee.pkmer.music.application.song.get;

import io.gitee.pkmer.convention.converter.TargetAndSourceConverter;
import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class GetSongHandler implements CommandHandler<GetSongCmd, SongDetailView> {
    private final SongRepository songRepository;
    private final String minioServer;
    public GetSongHandler(SongRepository songRepository,
                          PkmerMinioProps props) {
        this.songRepository = songRepository;
        this.minioServer = props.getUrl();
    }

    @Override
    public SongDetailView execute(GetSongCmd cmd) {
        SongAggregate song = songRepository.load(new SongId(cmd.getId())).orElseThrow(
                () -> new RuntimeException("歌曲不存在")
        );

        return toView(song);
    }

    private SongDetailView toView(SongAggregate song){
        return SongDetailView.builder()
                .id(song.getId().value().toString())
                .singerId(song.getSingerId().value().toString())
                .name(song.getName())
                .introduction(song.getIntroduction())
                .pic(buildMinioServerUrl(song.getPic()))
                .url(buildMinioServerUrl(song.getUrl()))
                .lyric(song.getLyric())
                .build();
    }

    private String buildMinioServerUrl(String url){
        return minioServer + url;
    }

}
