package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.util.List;

@Mapper
public interface CollectPageMapper {

    @SelectProvider(type = CollectPageProvider.class, method = "pageQueryCollectJoinSongAndSinger")
    @Results(id="CollectSongResult", value = {
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="song_id", property="songId", jdbcType=JdbcType.BIGINT),
            @Result(column="song_id", property="song.id", jdbcType=JdbcType.BIGINT),
            @Result(column="song_name", property="song.name", jdbcType=JdbcType.VARCHAR),
            @Result(column="singer_id", property="song.singerId", jdbcType=JdbcType.BIGINT),
            @Result(column="singer_name", property="song.singerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="song_url", property="song.url", jdbcType=JdbcType.VARCHAR),
            @Result(column="song_pic", property="song.pic", jdbcType=JdbcType.VARCHAR),
            @Result(column="song_introduction", property="song.introduction", jdbcType=JdbcType.VARCHAR),
    })
    /**
     * 分页查询收藏歌曲 left join song和singer
     * 参数接收SelectStatementProvider
     * 客户端传进来的参数直接在service层做字段的处理。
     */
    List<CollectSongDto> pageQueryCollectJoinSongAndSinger(SelectStatementProvider selectStatement);

    @SelectProvider(type = CollectPageProvider.class, method = "getCollectPageTotalJoinSongAndSinger")
    int getCollectPageTotalJoinSongAndSinger(SelectStatementProvider selectStatementProvider);










}
