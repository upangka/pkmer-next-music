package io.gitee.pkmer.music.application.singer.add;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.singer.Sex;
import io.gitee.pkmer.music.domain.singer.SingerAggrerate;
import io.gitee.pkmer.music.domain.singer.SingerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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
public class AddSingerHandler implements CommandHandler<AddSingerCmd,Void> {

    private final SingerRepository singerRepository;
    public AddSingerHandler(SingerRepository singerRepository){
        this.singerRepository = singerRepository;
    }

    @Override
    public Void execute(AddSingerCmd cmd) {
        SingerAggrerate singerAggrerate = buildSingerAggretate(cmd);
        singerRepository.save(singerAggrerate);
        return null;
    }

    public SingerAggrerate buildSingerAggretate(AddSingerCmd cmd){

        return SingerAggrerate.builder()
                .name(cmd.getName())
                .sex(cmd.getSex())
                .pic(cmd.getPic())
                .birth(cmd.getBirth())
                .location(cmd.getLocation())
                .songs(List.of())
                .build();

    }
}
