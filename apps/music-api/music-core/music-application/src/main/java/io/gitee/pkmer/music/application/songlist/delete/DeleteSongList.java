package io.gitee.pkmer.music.application.songlist.delete;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import org.springframework.stereotype.Component;

/**
 * 删除歌单
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Component
public class DeleteSongList implements CommandHandler<DeleteSongListCmd,Void> {
    private final SongListRepository repository;

    public DeleteSongList(SongListRepository songListRepository){
        this.repository = songListRepository;
    }

    @Override
    public Void execute(DeleteSongListCmd cmd) {
        SongListAggregate songListAggregate = repository.load(new SongListId(cmd.getId()));
        songListAggregate.toDelete();
        repository.save(songListAggregate);
        return null;
    }
}
