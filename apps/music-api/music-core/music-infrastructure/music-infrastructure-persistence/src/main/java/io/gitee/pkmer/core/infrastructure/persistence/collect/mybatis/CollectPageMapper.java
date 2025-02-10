package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.util.List;

@Mapper
public interface CollectPageMapper {
    @SelectProvider(type = CollectPageProvider.class, method = "selectWithLeftJoinProvider")
    @Results(id="CollectResult", value = {
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
            @Result(column="song_list_id", property="songListId", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="song_id", property="song.id", jdbcType=JdbcType.BIGINT),
            @Result(column="song_name", property="song.name", jdbcType=JdbcType.BIGINT),
    })
    List<CollectSongDto> selectWithLeftJoin(SelectStatementProvider selectStatementProvider);
}
