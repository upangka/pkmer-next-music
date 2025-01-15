package io.gitee.pkmer.music.domain.user;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import io.gitee.pkmer.music.domain.singer.Sex;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/11
 * </p>
 */
@Getter
@Builder
public class UserAggregate extends AuditableEntity implements AggregateRoot {
    private UserId id;
    private String username;
    private String password;
    private Sex sex;
    private String phoneNum;
    private String email;
    private LocalDateTime birth;
    private String introduction;
    private String location;
    private String avator;


}
