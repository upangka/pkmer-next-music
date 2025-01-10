package io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis;

import jakarta.annotation.Generated;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static io.gitee.pkmer.core.infrastructure.persistence.comment.mybatis.CommentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface CommentDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Comment>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, songId, songListId, content, createTime, type, up);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CommentResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="song_id", property="songId", jdbcType=JdbcType.BIGINT),
        @Result(column="song_list_id", property="songListId", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="up", property="up", jdbcType=JdbcType.INTEGER)
    })
    List<Comment> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CommentResult")
    Optional<Comment> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, comment, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, comment, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Comment row) {
        return MyBatis3Utils.insert(this::insert, row, comment, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(songId).toProperty("songId")
            .map(songListId).toProperty("songListId")
            .map(content).toProperty("content")
            .map(createTime).toProperty("createTime")
            .map(type).toProperty("type")
            .map(up).toProperty("up")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Comment> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, comment, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(songId).toProperty("songId")
            .map(songListId).toProperty("songListId")
            .map(content).toProperty("content")
            .map(createTime).toProperty("createTime")
            .map(type).toProperty("type")
            .map(up).toProperty("up")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Comment row) {
        return MyBatis3Utils.insert(this::insert, row, comment, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(songId).toPropertyWhenPresent("songId", row::getSongId)
            .map(songListId).toPropertyWhenPresent("songListId", row::getSongListId)
            .map(content).toPropertyWhenPresent("content", row::getContent)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(type).toPropertyWhenPresent("type", row::getType)
            .map(up).toPropertyWhenPresent("up", row::getUp)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Comment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, comment, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Comment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, comment, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Comment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, comment, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Comment> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, comment, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Comment row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(userId).equalTo(row::getUserId)
                .set(songId).equalTo(row::getSongId)
                .set(songListId).equalTo(row::getSongListId)
                .set(content).equalTo(row::getContent)
                .set(createTime).equalTo(row::getCreateTime)
                .set(type).equalTo(row::getType)
                .set(up).equalTo(row::getUp);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Comment row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(songId).equalToWhenPresent(row::getSongId)
                .set(songListId).equalToWhenPresent(row::getSongListId)
                .set(content).equalToWhenPresent(row::getContent)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(type).equalToWhenPresent(row::getType)
                .set(up).equalToWhenPresent(row::getUp);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Comment row) {
        return update(c ->
            c.set(userId).equalTo(row::getUserId)
            .set(songId).equalTo(row::getSongId)
            .set(songListId).equalTo(row::getSongListId)
            .set(content).equalTo(row::getContent)
            .set(createTime).equalTo(row::getCreateTime)
            .set(type).equalTo(row::getType)
            .set(up).equalTo(row::getUp)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Comment row) {
        return update(c ->
            c.set(userId).equalToWhenPresent(row::getUserId)
            .set(songId).equalToWhenPresent(row::getSongId)
            .set(songListId).equalToWhenPresent(row::getSongListId)
            .set(content).equalToWhenPresent(row::getContent)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(type).equalToWhenPresent(row::getType)
            .set(up).equalToWhenPresent(row::getUp)
            .where(id, isEqualTo(row::getId))
        );
    }
}