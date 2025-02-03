package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.collect.CollectAggregate;
import io.gitee.pkmer.music.domain.collect.CollectId;
import io.gitee.pkmer.music.domain.collect.CollectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
@RequiredArgsConstructor
@Component
public class CancelHandler implements CommandHandler<CancelCmd, Void> {
    private final CollectService collectService;
    private final CollectRepository collectRepository;

    @Override
    public Void execute(CancelCmd cancelCmd) {
        CollectView collectView = getCollectView(cancelCmd);
        CollectId collectId = new CollectId(collectView.getId());
        CollectAggregate collect = collectRepository.load(collectId).orElseThrow(
                () -> new IllegalArgumentException("收藏不存在")
        );
        collect.cancel();
        collectRepository.save(collect);
        return null;
    }

    private CollectView getCollectView(CancelCmd cancelCmd) {
        CollectView collectView;
        if (cancelCmd.isCanelCollectSong()) {
            collectView = collectService
                    .getCollectBySongId(cancelCmd.getUserId(), cancelCmd.getSongId())
                    .orElseThrow(() -> new IllegalArgumentException("收藏歌曲不存在"));
        } else if (cancelCmd.isCanelCollectSongList()) {
            collectView = collectService
                    .getCollectBySongListId(cancelCmd.getUserId(), cancelCmd.getSongId())
                    .orElseThrow(() -> new IllegalArgumentException("收藏歌曲不存在"));
        }else{
            throw new IllegalArgumentException("收藏参数错误");
        }
        return collectView;
    }
}
