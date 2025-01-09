package io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SingerDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Singer singer = new Singer();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = singer.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = singer.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sex = singer.sex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pic = singer.pic;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<LocalDateTime> birth = singer.birth;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> location = singer.location;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> introduction = singer.introduction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Singer extends AliasableSqlTable<Singer> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("`name`", JDBCType.VARCHAR);

        public final SqlColumn<Byte> sex = column("sex", JDBCType.TINYINT);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> birth = column("birth", JDBCType.TIMESTAMP);

        public final SqlColumn<String> location = column("`location`", JDBCType.VARCHAR);

        public final SqlColumn<String> introduction = column("introduction", JDBCType.VARCHAR);

        public Singer() {
            super("singer", Singer::new);
        }
    }
}