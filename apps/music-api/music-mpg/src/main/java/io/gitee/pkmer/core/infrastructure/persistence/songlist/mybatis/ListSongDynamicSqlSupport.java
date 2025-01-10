package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ListSongDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final ListSong listSong = new ListSong();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = listSong.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> songId = listSong.songId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> songListId = listSong.songListId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class ListSong extends AliasableSqlTable<ListSong> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> songId = column("song_id", JDBCType.BIGINT);

        public final SqlColumn<Long> songListId = column("song_list_id", JDBCType.BIGINT);

        public ListSong() {
            super("list_song", ListSong::new);
        }
    }
}