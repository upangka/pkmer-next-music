package io.gitee.pkmer.music.application.songlist.add;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListBuilderFactory;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/6
 */
@Component
public class AddSongListHandler implements CommandHandler<AddSongListCmd,Void> {
    private final SongListRepository repository;
    private final SongListBuilderFactory songListBuilderFactory;
    public AddSongListHandler(SongListRepository repository, SongListBuilderFactory songListBuilderFactory){
        this.repository = repository;
        this.songListBuilderFactory = songListBuilderFactory;
    }

    @Override
    public Void execute(AddSongListCmd cmd) {
        SongListAggregate aggregate = toAggregate(cmd);
        repository.save(aggregate);
        return null;
    }


    private SongListAggregate toAggregate(AddSongListCmd cmd) {
        return songListBuilderFactory.createSongListBuilder()
                .id(cmd.getId())
                .title(cmd.getTitle())
                .pic(cmd.getPic())
                .styles(cmd.getStyle())
                .introduction(cmd.getIntroduction())
                .build();
    }
}
