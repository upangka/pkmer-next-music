package io.gitee.pkmer.music.application.songlist.add;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import org.springframework.stereotype.Component;

/**
 *
 * 给歌单添加歌曲
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@Component
public class AddSongForListHandler implements CommandHandler<AddSongForListCmd,Void> {
    private final SongListRepository repository;

    public AddSongForListHandler(SongListRepository repository){
        this.repository = repository;
    }

    /**
     * 给歌单添加歌曲
     */
    @Override
    public Void execute(AddSongForListCmd cmd) {
        SongListAggregate songList = repository.load(new SongListId(cmd.getSongListId())).orElseThrow();
        cmd.getSongIds().forEach(songList::addSong);
        songList.toUpdate();
        repository.save(songList);
        return null;
    }
}
