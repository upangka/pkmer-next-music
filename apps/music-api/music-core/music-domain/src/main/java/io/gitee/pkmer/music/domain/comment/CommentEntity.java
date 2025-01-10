package io.gitee.pkmer.music.domain.comment;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.Entity;
import io.gitee.pkmer.music.domain.user.UserId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 评论实体
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentEntity extends AuditableEntity implements Entity {
    private Long id;
    private UserId userId;

    private String content;

    private LocalDateTime createTime;

    // todo 评论类型做枚举值对象
    private Byte type;
    // 点赞数
    private Integer up;
}
