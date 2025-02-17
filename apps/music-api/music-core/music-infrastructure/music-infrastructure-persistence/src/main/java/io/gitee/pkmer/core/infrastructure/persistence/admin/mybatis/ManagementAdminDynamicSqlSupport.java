package io.gitee.pkmer.core.infrastructure.persistence.admin.mybatis;

import jakarta.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;

public final class ManagementAdminDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final ManagementAdmin managementAdmin = new ManagementAdmin();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = managementAdmin.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = managementAdmin.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> password = managementAdmin.password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class ManagementAdmin extends AliasableSqlTable<ManagementAdmin> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("`name`", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("`password`", JDBCType.VARCHAR);

        public ManagementAdmin() {
            super("management_admin", ManagementAdmin::new);
        }
    }
}