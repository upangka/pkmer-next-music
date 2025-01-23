package io.gitee.pkmer.music.application.banner.all;

import io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.Banner;
import io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicMapper;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.banner;
import static io.gitee.pkmer.core.infrastructure.persistence.banner.mybatis.BannerDynamicSqlSupport.id;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.where;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

/**
 * CQRS分离的业务逻辑
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/8
 * </p>
 */
@Service
public class BannerService {
    private final BannerDynamicMapper bannerMapper;
    private final String minioServerAddress;
    public BannerService(BannerDynamicMapper bannerDynamicMapper,
                         PkmerMinioProps props){
        this.bannerMapper = bannerDynamicMapper;
        minioServerAddress = props.getUrl();
    }

    public List<BannerView> findAll(List<Long> ids) {
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

        List<Banner> banners = bannerMapper.selectMany(selectProvider);
        return convertFromList(banners);
    }

    private List<BannerView> convertFromList(List<Banner> banners){
        return banners.stream().map(banner ->
                        new BannerView()
                .setId(banner.getId().toString())
                .setUrl(handleUrl(banner.getPic())))
                .toList();
    }

    /**
     * 处理URL以获取完整的MinIO服务器地址
     * 此方法用于在给定的URL前添加MinIO服务器地址，以形成一个完整的访问路径
     * 这是因为直接访问MinIO服务时，需要使用其服务器地址作为基础URL
     *
     * @param url 待处理的URL，通常是MinIO对象的相对路径
     * @return 返回拼接了MinIO服务器地址的完整URL
     */
    private String handleUrl(String url){
        return minioServerAddress + url;
    }

}
