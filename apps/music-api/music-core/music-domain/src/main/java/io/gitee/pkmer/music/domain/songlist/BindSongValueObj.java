package io.gitee.pkmer.music.domain.songlist;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.ValueObject;
import io.gitee.pkmer.music.domain.song.SongId;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
@AllArgsConstructor
@Getter
public class BindSongValueObj extends AuditableEntity implements ValueObject {
    private SongId songId;
    private SongListId songListId;
}
