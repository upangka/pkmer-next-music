package io.gitee.pkmer.music.application.songlist.update;

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
public class UpdateCommentsHandler implements CommandHandler<UpdateSongListCommentCmd,Void> {

    private final SongListRepository repository;
    public UpdateCommentsHandler(SongListRepository repository){
        this.repository = repository;
    }

    @Override
    public Void execute(UpdateSongListCommentCmd cmd) {
        SongListAggregate songList = repository.load(new SongListId(cmd.getSongListId()));
        if(songList != null){
            songList.modifyComment(cmd.getCommentId(),cmd.getContent());
            songList.toUpdate();
            repository.save(songList);
        }
        return null;
    }
}
