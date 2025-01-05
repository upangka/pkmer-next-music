package io.gitee.pkmer.music.domain.banner;

import io.gitee.pkmer.ddd.id.Identifier;
import lombok.AllArgsConstructor;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@AllArgsConstructor
public class BannerId implements Identifier<Long> {
    private Long id;
    @Override
    public Long value() {
        return id;
    }
}