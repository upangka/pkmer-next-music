package io.gitee.pkmer.music.application.singer.delete;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.singer.SingerAggrerate;
import io.gitee.pkmer.music.domain.singer.SingerId;
import io.gitee.pkmer.music.domain.singer.SingerRepository;
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
public class DeleteSingerHandler implements CommandHandler<DeleteSingerCmd,Void> {

    private final SingerRepository singerRepository;
    public DeleteSingerHandler(SingerRepository singerRepository){
        this.singerRepository = singerRepository;
    }

    @Override
    public Void execute(DeleteSingerCmd deleteSingerCmd) {
        SingerAggrerate singer = singerRepository.load(new SingerId(deleteSingerCmd.getId())).orElseThrow();
        singer.toDelete();
        singerRepository.save(singer);
        return null;
    }
}
