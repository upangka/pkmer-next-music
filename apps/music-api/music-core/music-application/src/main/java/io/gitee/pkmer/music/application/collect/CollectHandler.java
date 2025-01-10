package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.collect.CollectAggregate;
import io.gitee.pkmer.music.domain.collect.CollectRepository;
import io.gitee.pkmer.music.domain.song.SongId;
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
public class CollectHandler implements CommandHandler<CollectCmd,Void> {
    // 使用构造方法注入CollectRepository
    private final CollectRepository collectRepository;
    public CollectHandler(CollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

    @Override
    public Void execute(CollectCmd collectCmd) {
        CollectAggregate collect = CollectAggregate.builder()
                .userId(new UserId(collectCmd.getUserId()))
                .build();
        if(collectCmd.isCollectSong()){
            // 收藏歌曲
            collect.collectSong(new SongId(collectCmd.getSongId()));
        }else if (collectCmd.isCollectSongList()){
            // 收藏歌单
            collect.collectSongList(new SongListId(collectCmd.getSongListId()));
        }else{
            throw new IllegalArgumentException("收藏参数错误");
        }

        collectRepository.save(collect);
        return null;
    }
}
