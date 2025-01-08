package io.gitee.pkmer.music.application.song.delete;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.song.SongAggregate;
import io.gitee.pkmer.music.domain.song.SongId;
import io.gitee.pkmer.music.domain.song.SongRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/9
 * </p>
 */
@Component
public class DeleteSongHandler implements CommandHandler<DeleteCmd, Void> {

    private final SongRepository songRepository;

    public DeleteSongHandler(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    @Override
    public Void execute(DeleteCmd cmd) {
        SongAggregate song = songRepository.load(new SongId(cmd.getId()));
        song.toDelete();
        songRepository.save(song);
        return null;
    }
}
