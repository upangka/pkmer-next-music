package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Data
@Accessors(chain = true)
public class CollectCmd implements Command {
    private Long userId;
    private Long songId;
    private Long songListId;

    /**
     * 是否收藏歌曲
     * @return
     */
    boolean isCollectSong(){
        return songId != null && songListId == null;
    }

    /**
     * 是否收藏歌单
     */
    boolean isCollectSongList(){
        return songId == null && songListId != null;
    }
}
