package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.collect.CollectAggregate;
import io.gitee.pkmer.music.domain.collect.CollectId;
import io.gitee.pkmer.music.domain.collect.CollectRepository;
import org.springframework.stereotype.Component;

/**
 * 取消收藏
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
public class CancelHandler implements CommandHandler<CancelCmd,Void> {
    private final  CollectRepository collectRepository;
    public CancelHandler(CollectRepository collectRepository){
        this.collectRepository = collectRepository;
    }
    @Override
    public Void execute(CancelCmd cancelCmd) {
        CollectAggregate collect = collectRepository.load(new CollectId(cancelCmd.getId()));
        if(collect != null){
            collect.cancel();
            collectRepository.save(collect);
        }
        return null;
    }
}
