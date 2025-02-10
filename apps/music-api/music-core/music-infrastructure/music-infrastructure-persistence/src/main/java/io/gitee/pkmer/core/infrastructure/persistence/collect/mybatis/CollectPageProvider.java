package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;


import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

public class CollectPageProvider {

    /**
     * 分页查询收藏歌曲 left join song和singer
     */
    public String selectWithLeftJoinProvider(SelectStatementProvider selectStatement) {
        return selectStatement.getSelectStatement();
    }

}
