package io.gitee.pkmer.music.domain.banner;

import io.gitee.pkmer.ddd.EntityAggregate;
import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.id.Identifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */

@Getter
@AllArgsConstructor
public class BannerEntity extends AuditableEntity implements EntityAggregate {
    private BannerId id;
    private String pic;
}
