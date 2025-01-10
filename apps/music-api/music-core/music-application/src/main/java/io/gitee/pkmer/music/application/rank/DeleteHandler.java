package io.gitee.pkmer.music.application.rank;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.rank.RankAggregate;
import io.gitee.pkmer.music.domain.rank.RankId;
import io.gitee.pkmer.music.domain.rank.RankRepository;
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
public class DeleteHandler implements CommandHandler<DeleteRankCmd,Void> {
    private final RankRepository rankRepository;
    public DeleteHandler(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @Override
    public Void execute(DeleteRankCmd deleteCmd) {
        RankAggregate rank = rankRepository.load(new RankId(deleteCmd.getId()));
        if(rank != null){
            rank.toDelete();
            rankRepository.save(rank);
        }
        return null;
    }
}
