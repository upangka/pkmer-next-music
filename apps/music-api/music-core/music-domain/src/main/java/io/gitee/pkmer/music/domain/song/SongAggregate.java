package io.gitee.pkmer.music.domain.song;

import io.gitee.pkmer.ddd.EntityAggregate;
import io.gitee.pkmer.ddd.common.AuditableEntity;
import lombok.Data;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/9
 * </p>
 */
@Data
public class SongAggregate extends AuditableEntity implements EntityAggregate {
    private SongId id;
    private SingerId singerId;

    private String name;

    private String introduction;

    private String pic;

    private String url;

    private String lyric;
}
