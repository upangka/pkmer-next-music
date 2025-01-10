package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import jakarta.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;

public final class ListSongDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final ListSong listSong = new ListSong();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = listSong.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> songId = listSong.songId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> songListId = listSong.songListId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class ListSong extends AliasableSqlTable<ListSong> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> songId = column("song_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> songListId = column("song_list_id", JDBCType.INTEGER);

        public ListSong() {
            super("list_song", ListSong::new);
        }
    }
}