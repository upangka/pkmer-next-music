package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;


import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;


public class CollectPageProvider {

    public String selectWithLeftJoinProvider(SelectStatementProvider selectStatementProvider){
        return selectStatementProvider.getSelectStatement();
//        String sql =  select(CollectDynamicSqlSupport.collect.allColumns())
//                .from(CollectDynamicSqlSupport.collect)
//                .leftJoin(SongDynamicSqlSupport.song)
//                .on(CollectDynamicSqlSupport.collect.songId,equalTo(SongDynamicSqlSupport.song.id))
//                .where(SongDynamicSqlSupport.song.name,isLike(songName).map(s -> "%" + s + "%"))
//                .and(CollectDynamicSqlSupport.collect.type,isEqualTo((byte)1))
//                .orderBy(CollectDynamicSqlSupport.collect.createTime.descending())
//                .limit(3)
//                .offset(2)
//                .offset((long) (pageNo - 1) * pageSize)
//                .build()
//                .render(RenderingStrategies.MYBATIS3)
//                .getSelectStatement();
//
//
//        System.out.println(sql);
//        return sql;

    }
}
