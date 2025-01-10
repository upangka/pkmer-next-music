package io.gitee.pkmer.music.application.rank;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.rank.RankAggregate;
import io.gitee.pkmer.music.domain.rank.RankRepository;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.user.UserId;
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
public class AddRankHandler implements CommandHandler<AddRankCmd,Void> {
    // 构造方法给我注入RankRepository
    private final RankRepository rankRepository;
    public AddRankHandler(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @Override
    public Void execute(AddRankCmd addCmd) {
        RankAggregate rank = RankAggregate.builder()
                .consumerId(new UserId(addCmd.getConsumerId()))
                .score(addCmd.getScore())
                .songListId(new SongListId(addCmd.getSongListId()))
                .build();

        rankRepository.save(rank);

        return null;
    }
}
