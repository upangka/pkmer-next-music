package io.gitee.pkmer.music.domain.banner;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.Repository;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
public interface BannerRepository extends Repository<BannerEntity,BannerId> {
    List<BannerEntity> findAll(List<Long> ids);
}
