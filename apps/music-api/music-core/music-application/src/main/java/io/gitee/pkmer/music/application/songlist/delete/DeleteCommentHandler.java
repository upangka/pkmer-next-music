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
public class DeleteCommentHandler implements CommandHandler<DeleteCommentCmd,Void> {

    private final SongListRepository repository;

    public DeleteCommentHandler(SongListRepository repository){
       this.repository = repository;
   }

    @Override
    public Void execute(DeleteCommentCmd cmd) {
        SongListAggregate songList = repository.load(new SongListId(cmd.getSongListId())).orElseThrow();
        songList.deleteComment(cmd.getCommentId());
        songList.toUpdate();
        repository.save(songList);
        return null;
    }
}
