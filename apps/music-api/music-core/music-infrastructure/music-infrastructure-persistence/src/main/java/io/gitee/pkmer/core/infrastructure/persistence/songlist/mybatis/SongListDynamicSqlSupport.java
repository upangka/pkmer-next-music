package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import jakarta.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;

public final class SongListDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SongList songList = new SongList();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = songList.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> title = songList.title;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pic = songList.pic;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> style = songList.style;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> introduction = songList.introduction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class SongList extends AliasableSqlTable<SongList> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public final SqlColumn<String> style = column("`style`", JDBCType.VARCHAR);

        public final SqlColumn<String> introduction = column("introduction", JDBCType.LONGVARCHAR);

        public SongList() {
            super("song_list", SongList::new);
        }
    }
}