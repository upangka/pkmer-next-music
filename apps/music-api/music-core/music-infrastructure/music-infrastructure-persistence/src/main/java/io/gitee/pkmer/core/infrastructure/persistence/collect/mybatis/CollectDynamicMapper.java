package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;

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

import static io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface CollectDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Collect>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, type, songId, songListId, createTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CollectResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="song_id", property="songId", jdbcType=JdbcType.BIGINT),
        @Result(column="song_list_id", property="songListId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Collect> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CollectResult")
    Optional<Collect> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, collect, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, collect, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Collect row) {
        return MyBatis3Utils.insert(this::insert, row, collect, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(type).toProperty("type")
            .map(songId).toProperty("songId")
            .map(songListId).toProperty("songListId")
            .map(createTime).toProperty("createTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Collect> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, collect, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(type).toProperty("type")
            .map(songId).toProperty("songId")
            .map(songListId).toProperty("songListId")
            .map(createTime).toProperty("createTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Collect row) {
        return MyBatis3Utils.insert(this::insert, row, collect, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(type).toPropertyWhenPresent("type", row::getType)
            .map(songId).toPropertyWhenPresent("songId", row::getSongId)
            .map(songListId).toPropertyWhenPresent("songListId", row::getSongListId)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Collect> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, collect, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Collect> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, collect, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Collect> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, collect, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Collect> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, collect, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Collect row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(userId).equalTo(row::getUserId)
                .set(type).equalTo(row::getType)
                .set(songId).equalTo(row::getSongId)
                .set(songListId).equalTo(row::getSongListId)
                .set(createTime).equalTo(row::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Collect row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(type).equalToWhenPresent(row::getType)
                .set(songId).equalToWhenPresent(row::getSongId)
                .set(songListId).equalToWhenPresent(row::getSongListId)
                .set(createTime).equalToWhenPresent(row::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Collect row) {
        return update(c ->
            c.set(userId).equalTo(row::getUserId)
            .set(type).equalTo(row::getType)
            .set(songId).equalTo(row::getSongId)
            .set(songListId).equalTo(row::getSongListId)
            .set(createTime).equalTo(row::getCreateTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Collect row) {
        return update(c ->
            c.set(userId).equalToWhenPresent(row::getUserId)
            .set(type).equalToWhenPresent(row::getType)
            .set(songId).equalToWhenPresent(row::getSongId)
            .set(songListId).equalToWhenPresent(row::getSongListId)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}