package io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis;

import static io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

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
public interface SingerDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Singer>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, sex, pic, birth, location, introduction);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SingerResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR)
    })
    List<Singer> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SingerResult")
    Optional<Singer> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, singer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, singer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Singer row) {
        return MyBatis3Utils.insert(this::insert, row, singer, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(sex).toProperty("sex")
            .map(pic).toProperty("pic")
            .map(birth).toProperty("birth")
            .map(location).toProperty("location")
            .map(introduction).toProperty("introduction")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Singer> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, singer, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(sex).toProperty("sex")
            .map(pic).toProperty("pic")
            .map(birth).toProperty("birth")
            .map(location).toProperty("location")
            .map(introduction).toProperty("introduction")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Singer row) {
        return MyBatis3Utils.insert(this::insert, row, singer, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(sex).toPropertyWhenPresent("sex", row::getSex)
            .map(pic).toPropertyWhenPresent("pic", row::getPic)
            .map(birth).toPropertyWhenPresent("birth", row::getBirth)
            .map(location).toPropertyWhenPresent("location", row::getLocation)
            .map(introduction).toPropertyWhenPresent("introduction", row::getIntroduction)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Singer> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, singer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Singer> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, singer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Singer> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, singer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Singer> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, singer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Singer row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(name).equalTo(row::getName)
                .set(sex).equalTo(row::getSex)
                .set(pic).equalTo(row::getPic)
                .set(birth).equalTo(row::getBirth)
                .set(location).equalTo(row::getLocation)
                .set(introduction).equalTo(row::getIntroduction);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Singer row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(name).equalToWhenPresent(row::getName)
                .set(sex).equalToWhenPresent(row::getSex)
                .set(pic).equalToWhenPresent(row::getPic)
                .set(birth).equalToWhenPresent(row::getBirth)
                .set(location).equalToWhenPresent(row::getLocation)
                .set(introduction).equalToWhenPresent(row::getIntroduction);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Singer row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(sex).equalTo(row::getSex)
            .set(pic).equalTo(row::getPic)
            .set(birth).equalTo(row::getBirth)
            .set(location).equalTo(row::getLocation)
            .set(introduction).equalTo(row::getIntroduction)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Singer row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(sex).equalToWhenPresent(row::getSex)
            .set(pic).equalToWhenPresent(row::getPic)
            .set(birth).equalToWhenPresent(row::getBirth)
            .set(location).equalToWhenPresent(row::getLocation)
            .set(introduction).equalToWhenPresent(row::getIntroduction)
            .where(id, isEqualTo(row::getId))
        );
    }
}