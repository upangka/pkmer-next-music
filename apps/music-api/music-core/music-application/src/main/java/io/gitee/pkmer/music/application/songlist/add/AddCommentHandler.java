package io.gitee.pkmer.music.application.songlist.add;

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
public class AddCommentHandler implements CommandHandler<AddCommentCmd,Void> {

    private final SongListRepository repository;
    public AddCommentHandler(SongListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void execute(AddCommentCmd cmd) {
        SongListAggregate songList = repository.load(new SongListId(cmd.getSongListId())).orElseThrow();
        songList.addComment(cmd.getComment());
        songList.toUpdate();
        repository.save(songList);
        return null;
    }
}
