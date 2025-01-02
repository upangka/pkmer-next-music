package io.gitee.pkmer.core.dao.mapper.banner;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class BannerDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Banner banner = new Banner();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = banner.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pic = banner.pic;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Banner extends AliasableSqlTable<Banner> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public Banner() {
            super("banner", Banner::new);
        }
    }
}