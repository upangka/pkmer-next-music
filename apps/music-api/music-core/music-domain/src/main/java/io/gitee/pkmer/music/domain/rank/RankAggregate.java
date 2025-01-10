package io.gitee.pkmer.music.domain.rank;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.user.UserId;
import lombok.Builder;
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
@Getter
@Builder
public class RankAggregate extends AuditableEntity implements AggregateRoot {
    private RankId id;
    private SongListId songListId;
    private UserId consumerId;
    private Integer score;
}
