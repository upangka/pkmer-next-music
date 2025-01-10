package io.gitee.pkmer.music.application.songlist.delete;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import org.springframework.stereotype.Component;

/**
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
public class DeleteSongForListHandler implements CommandHandler<DeleteSongForListCmd,Void> {

    private final SongListRepository repository;

    public DeleteSongForListHandler(SongListRepository repository) {
        this.repository = repository;
    }

    /**
     * 删除歌单中的某个歌曲
     */
    @Override
    public Void execute(DeleteSongForListCmd cmd) {
        SongListAggregate songList = repository.load(new SongListId(cmd.getSongListId()));
        if(songList != null){
            cmd.getSongIds().forEach(songList::deleteSong);
            repository.save(songList);
        }
        return null;
    }
}
