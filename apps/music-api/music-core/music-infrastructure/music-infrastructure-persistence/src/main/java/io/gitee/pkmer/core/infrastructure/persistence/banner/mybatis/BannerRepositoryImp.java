package io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis;

import io.gitee.pkmer.music.domain.banner.BannerEntity;
import io.gitee.pkmer.music.domain.banner.BannerId;
import io.gitee.pkmer.music.domain.banner.BannerRepository;

import org.springframework.stereotype.Repository;

import static org.mybatis.dynamic.sql.select.SelectDSL.select;


/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Repository
public class BannerRepositoryImp implements BannerRepository{

    private final BannerDynamicMapper bannerMapper;

    public BannerRepositoryImp(BannerDynamicMapper bannerDynamicMapper) {
        this.bannerMapper = bannerDynamicMapper;
    }

    @Override
    public BannerEntity load(BannerId bannerId) {
        throw new UnsupportedOperationException("未实现");
    }

    @Override
    public void save(BannerEntity banner) {
        switch (banner.getChangingStatus()) {
            case NEW:
                insert(banner);
            default:
                throw new RuntimeException("不支持的操作");
        }
    }


    private void insert(BannerEntity entity) {
        Banner record = new Banner();
        record.setPic(entity.getPic());
        bannerMapper.insert(record);
    }


}
