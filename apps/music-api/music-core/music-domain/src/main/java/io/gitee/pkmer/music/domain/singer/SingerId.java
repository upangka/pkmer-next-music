package io.gitee.pkmer.music.domain.singer;

import io.gitee.pkmer.ddd.id.Identifier;
import lombok.AllArgsConstructor;

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
public class SingerId implements Identifier<Long> {
    private Long id;
    @Override
    public Long value() {
        return id;
    }
}
