package io.gitee.pkmer.music.application.singer.update;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.singer.Sex;
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
public class UpdateSingerHandler implements CommandHandler<UpdateSingerCmd,Void> {

    private final SingerRepository singerRepository;
    public UpdateSingerHandler(SingerRepository singerRepository){
        this.singerRepository = singerRepository;
    }

    @Override
    public Void execute(UpdateSingerCmd cmd) {
        SingerAggrerate singer = singerRepository.load(new SingerId(cmd.getId()));

        if(singer != null){
            singer.toUpdate();
            updateSinger(singer, cmd);
            singerRepository.save(singer);
        }

        return null;
    }

    private void updateSinger(SingerAggrerate singer,UpdateSingerCmd cmd){
        singer.modifyName(cmd.getName());
        singer.modifyPic(cmd.getPic());
        singer.modifyBirth(cmd.getBirth());
        singer.modifyLocation(cmd.getLocation());
        singer.modifyIntroduction(cmd.getIntroduction());
        singer.modifySex(Sex.valueOf(cmd.getSex()));
    }

}
