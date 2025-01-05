package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

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

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface SongListDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<SongList>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, title, pic, style, introduction);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SongListResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="style", property="style", jdbcType=JdbcType.VARCHAR),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SongList> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SongListResult")
    Optional<SongList> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, songList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, songList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SongList row) {
        return MyBatis3Utils.insert(this::insert, row, songList, c ->
            c.map(id).toProperty("id")
            .map(title).toProperty("title")
            .map(pic).toProperty("pic")
            .map(style).toProperty("style")
            .map(introduction).toProperty("introduction")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<SongList> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, songList, c ->
            c.map(id).toProperty("id")
            .map(title).toProperty("title")
            .map(pic).toProperty("pic")
            .map(style).toProperty("style")
            .map(introduction).toProperty("introduction")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SongList row) {
        return MyBatis3Utils.insert(this::insert, row, songList, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(pic).toPropertyWhenPresent("pic", row::getPic)
            .map(style).toPropertyWhenPresent("style", row::getStyle)
            .map(introduction).toPropertyWhenPresent("introduction", row::getIntroduction)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SongList> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, songList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SongList> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, songList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SongList> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, songList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SongList> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, songList, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(SongList row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(title).equalTo(row::getTitle)
                .set(pic).equalTo(row::getPic)
                .set(style).equalTo(row::getStyle)
                .set(introduction).equalTo(row::getIntroduction);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SongList row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(pic).equalToWhenPresent(row::getPic)
                .set(style).equalToWhenPresent(row::getStyle)
                .set(introduction).equalToWhenPresent(row::getIntroduction);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SongList row) {
        return update(c ->
            c.set(title).equalTo(row::getTitle)
            .set(pic).equalTo(row::getPic)
            .set(style).equalTo(row::getStyle)
            .set(introduction).equalTo(row::getIntroduction)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SongList row) {
        return update(c ->
            c.set(title).equalToWhenPresent(row::getTitle)
            .set(pic).equalToWhenPresent(row::getPic)
            .set(style).equalToWhenPresent(row::getStyle)
            .set(introduction).equalToWhenPresent(row::getIntroduction)
            .where(id, isEqualTo(row::getId))
        );
    }
}