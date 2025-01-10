package io.gitee.pkmer.music.domain.collect;

import io.gitee.pkmer.ddd.id.Identifier;

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
public class CollectId implements Identifier<Long> {
    private final Long id;

    public CollectId(Long value) {
        this.id = value;
    }

    @Override
    public Long value() {
        return id;
    }
}
