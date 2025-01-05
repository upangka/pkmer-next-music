package io.gitee.pkmer.core.infrastructure.persistence.banner;

import io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.Banner;
import io.gitee.pkmer.music.domain.banner.BannerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Mapper(componentModel = "spring")
public interface BannerConverter {
    @Mapping(source = "id", target = "id.id")
    BannerEntity toEntity(Banner banner);
    List<BannerEntity> toEntities(List<Banner> banners);
}
