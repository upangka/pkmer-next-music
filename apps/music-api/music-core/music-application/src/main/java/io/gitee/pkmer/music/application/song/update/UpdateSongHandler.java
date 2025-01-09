package io.gitee.pkmer.music.application.song.update;

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
 * @since 2025/1/10
 * </p>
 */
@Component
public class UpdateSongHandler implements CommandHandler<UpdateSongCmd,Void> {

    private final SongRepository songRepository;
    public UpdateSongHandler(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    @Override
    public Void execute(UpdateSongCmd cmd) {
        SongAggregate song = songRepository.load(new SongId(cmd.getId()));
        if(song != null){
            updateSong(song,cmd);
            song.toUpdate();
            songRepository.save(song);
        }
        return null;
    }

    private void updateSong(SongAggregate song, UpdateSongCmd cmd){
        song.modifyName(cmd.getName());
        song.modifyIntroduction(cmd.getIntroduction());
        song.modifyPic(cmd.getPic());
        song.modifyUrl(cmd.getUrl());
        song.modifyLyric(cmd.getLyric());
    }
}
