package io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis;

import io.gitee.pkmer.core.infrastructure.persistence.banner.BannerConverter;
import io.gitee.pkmer.music.domain.banner.BannerEntity;
import io.gitee.pkmer.music.domain.banner.BannerId;
import io.gitee.pkmer.music.domain.banner.BannerRepository;

import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.banner;
import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.id;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
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
    private final BannerConverter converter;

    public BannerRepositoryImp(BannerDynamicMapper bannerDynamicMapper,
                               BannerConverter converter) {
        this.bannerMapper = bannerDynamicMapper;
        this.converter = converter;
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

    @Override
    public List<BannerEntity> findAll(List<Long> ids) {
        WhereApplier whereApplier = where().toWhereApplier();
        if(ids != null && !ids.isEmpty()){
            whereApplier = where(id, isIn(ids)).toWhereApplier();
        }

        SelectStatementProvider selectProvider = select(banner.allColumns())
                .from(banner)
                .applyWhere(whereApplier)
                .configureStatement(c -> c.setNonRenderingWhereClauseAllowed(true))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        System.out.println(selectProvider.getSelectStatement());

        List<Banner> banners = bannerMapper.selectMany(selectProvider);
        return converter.toEntities(banners);
    }


}
