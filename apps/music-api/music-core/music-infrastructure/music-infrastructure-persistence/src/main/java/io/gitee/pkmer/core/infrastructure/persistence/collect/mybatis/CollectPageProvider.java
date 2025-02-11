package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;


import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

public class CollectPageProvider {

    /**
     * 分页查询收藏歌曲 left join song和singer
     */
    public String pageQueryCollectJoinSongAndSinger(SelectStatementProvider selectStatement) {
        return selectStatement.getSelectStatement();
    }


    /**
     * 分页查询收藏歌曲总数 left join song和singer
     */
    public String getCollectPageTotalJoinSongAndSinger(SelectStatementProvider selectStatementProvider) {
        return selectStatementProvider.getSelectStatement();
    }
}
