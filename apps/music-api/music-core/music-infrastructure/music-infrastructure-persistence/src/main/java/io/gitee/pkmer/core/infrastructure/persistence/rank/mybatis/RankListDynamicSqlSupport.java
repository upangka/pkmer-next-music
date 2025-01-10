package io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis;

import jakarta.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;

public final class RankListDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RankList rankList = new RankList();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = rankList.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> songListId = rankList.songListId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> consumerId = rankList.consumerId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> score = rankList.score;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RankList extends AliasableSqlTable<RankList> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> songListId = column("song_list_id", JDBCType.BIGINT);

        public final SqlColumn<Long> consumerId = column("consumer_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> score = column("score", JDBCType.INTEGER);

        public RankList() {
            super("rank_list", RankList::new);
        }
    }
}