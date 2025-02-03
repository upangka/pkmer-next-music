package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Getter;
import lombok.Value;

/**
 * <p>
 *  取消收藏命令
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@Getter
@Value(staticConstructor = "commandOf")
public class CancelCmd implements Command {
    Long userId;
    Long songId;
    Long songListId;

    /**
     * 是否是取消收藏歌曲
     * @return
     */
    public boolean isCanelCollectSong() {
        return songId != null && songListId == null;
    }

    /**
     * 是否是取消收藏歌单
     * @return
     */
    public boolean isCanelCollectSongList() {
        return songId == null && songListId != null;
    }
}
