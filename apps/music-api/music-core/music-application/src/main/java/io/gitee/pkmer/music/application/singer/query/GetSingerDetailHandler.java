package io.gitee.pkmer.music.application.singer.query;


import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.singer.SingerAggrerate;
import io.gitee.pkmer.music.domain.singer.SingerId;
import io.gitee.pkmer.music.domain.singer.SingerRepository;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GetSingerDetailHandler implements CommandHandler<GetSingerDetailCmd,SingerView> {

   private final PkmerMinioProps minioProps;
   private final SingerRepository singerRepository;

    @Override
    public SingerView execute(GetSingerDetailCmd cmd) {
        SingerAggrerate singer = singerRepository.load(new SingerId(cmd.getId())).orElseThrow(
                () -> new RuntimeException("歌手不存在")
        );
        return toView(singer);
    }


    private SingerView toView(SingerAggrerate singer) {

        List<SingerView.SongView> songs = getSongViews(singer);

        return SingerView.builder()
                .id(singer.getSingerId().value().toString())
                .name(singer.getName())
                .sex(singer.getSex().name())
                .pic(addMinioServer(singer.getPic()))
                .birth(singer.getBirth())
                .location(singer.getLocation())
                .introduction(singer.getIntroduction())
                .songs(songs)
                .build();
    }

    @NotNull
    private List<SingerView.SongView> getSongViews(SingerAggrerate singer) {
        List<SingerView.SongView> songs = new ArrayList<>();

        for (SongAggregate song : singer.getSongs()) {
            SingerView.SongView result = SingerView.SongView.builder()
                    .id(song.getId().value().toString())
                    .name(song.getName())
                    .introduction(song.getIntroduction())
                    .picture(addMinioServer(song.getPic()))
                    .link(addMinioServer(song.getUrl()))
                    .build();
            songs.add(result);
        }
        return songs;
    }

    private String addMinioServer(String pic) {
        return minioProps.getUrl() + pic;
    }
}
