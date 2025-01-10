package io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis;

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

import static io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankListDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RankDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RankList>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, songListId, consumerId, score);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RankListResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="song_list_id", property="songListId", jdbcType=JdbcType.BIGINT),
        @Result(column="consumer_id", property="consumerId", jdbcType=JdbcType.BIGINT),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER)
    })
    List<RankList> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RankListResult")
    Optional<RankList> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rankList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rankList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RankList row) {
        return MyBatis3Utils.insert(this::insert, row, rankList, c ->
            c.map(id).toProperty("id")
            .map(songListId).toProperty("songListId")
            .map(consumerId).toProperty("consumerId")
            .map(score).toProperty("score")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<RankList> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rankList, c ->
            c.map(id).toProperty("id")
            .map(songListId).toProperty("songListId")
            .map(consumerId).toProperty("consumerId")
            .map(score).toProperty("score")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RankList row) {
        return MyBatis3Utils.insert(this::insert, row, rankList, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(songListId).toPropertyWhenPresent("songListId", row::getSongListId)
            .map(consumerId).toPropertyWhenPresent("consumerId", row::getConsumerId)
            .map(score).toPropertyWhenPresent("score", row::getScore)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RankList> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rankList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RankList> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rankList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RankList> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rankList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RankList> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rankList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(RankList row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(songListId).equalTo(row::getSongListId)
                .set(consumerId).equalTo(row::getConsumerId)
                .set(score).equalTo(row::getScore);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RankList row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(songListId).equalToWhenPresent(row::getSongListId)
                .set(consumerId).equalToWhenPresent(row::getConsumerId)
                .set(score).equalToWhenPresent(row::getScore);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RankList row) {
        return update(c ->
            c.set(songListId).equalTo(row::getSongListId)
            .set(consumerId).equalTo(row::getConsumerId)
            .set(score).equalTo(row::getScore)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RankList row) {
        return update(c ->
            c.set(songListId).equalToWhenPresent(row::getSongListId)
            .set(consumerId).equalToWhenPresent(row::getConsumerId)
            .set(score).equalToWhenPresent(row::getScore)
            .where(id, isEqualTo(row::getId))
        );
    }
}