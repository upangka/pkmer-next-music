package io.gitee.pkmer.core.infrastructure.persistence.song.mybatis;

import static io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.SongDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.gitee.pkmer.core.infrastructure.persistence.song.mybatis.Song;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
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
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface SongDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Song>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, singerId, name, introduction, createTime, updateTime, pic, url, lyric);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SongResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="singer_id", property="singerId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="lyric", property="lyric", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Song> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SongResult")
    Optional<Song> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, song, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, song, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Song row) {
        return MyBatis3Utils.insert(this::insert, row, song, c ->
            c.map(id).toProperty("id")
            .map(singerId).toProperty("singerId")
            .map(name).toProperty("name")
            .map(introduction).toProperty("introduction")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(pic).toProperty("pic")
            .map(url).toProperty("url")
            .map(lyric).toProperty("lyric")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Song> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, song, c ->
            c.map(id).toProperty("id")
            .map(singerId).toProperty("singerId")
            .map(name).toProperty("name")
            .map(introduction).toProperty("introduction")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(pic).toProperty("pic")
            .map(url).toProperty("url")
            .map(lyric).toProperty("lyric")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Song row) {
        return MyBatis3Utils.insert(this::insert, row, song, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(singerId).toPropertyWhenPresent("singerId", row::getSingerId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(introduction).toPropertyWhenPresent("introduction", row::getIntroduction)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(pic).toPropertyWhenPresent("pic", row::getPic)
            .map(url).toPropertyWhenPresent("url", row::getUrl)
            .map(lyric).toPropertyWhenPresent("lyric", row::getLyric)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Song> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, song, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Song> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, song, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Song> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, song, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Song> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, song, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Song row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(singerId).equalTo(row::getSingerId)
                .set(name).equalTo(row::getName)
                .set(introduction).equalTo(row::getIntroduction)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(pic).equalTo(row::getPic)
                .set(url).equalTo(row::getUrl)
                .set(lyric).equalTo(row::getLyric);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Song row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(singerId).equalToWhenPresent(row::getSingerId)
                .set(name).equalToWhenPresent(row::getName)
                .set(introduction).equalToWhenPresent(row::getIntroduction)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(pic).equalToWhenPresent(row::getPic)
                .set(url).equalToWhenPresent(row::getUrl)
                .set(lyric).equalToWhenPresent(row::getLyric);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Song row) {
        return update(c ->
            c.set(singerId).equalTo(row::getSingerId)
            .set(name).equalTo(row::getName)
            .set(introduction).equalTo(row::getIntroduction)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(pic).equalTo(row::getPic)
            .set(url).equalTo(row::getUrl)
            .set(lyric).equalTo(row::getLyric)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Song row) {
        return update(c ->
            c.set(singerId).equalToWhenPresent(row::getSingerId)
            .set(name).equalToWhenPresent(row::getName)
            .set(introduction).equalToWhenPresent(row::getIntroduction)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(pic).equalToWhenPresent(row::getPic)
            .set(url).equalToWhenPresent(row::getUrl)
            .set(lyric).equalToWhenPresent(row::getLyric)
            .where(id, isEqualTo(row::getId))
        );
    }
}