package io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis;

import static io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicSqlSupport.*;
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
public interface ConsumerDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Consumer>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, password, sex, phoneNum, email, birth, introduction, location, avator, createTime, updateTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ConsumerResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="phone_num", property="phoneNum", jdbcType=JdbcType.CHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.CHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="avator", property="avator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Consumer> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ConsumerResult")
    Optional<Consumer> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, consumer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, consumer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Consumer row) {
        return MyBatis3Utils.insert(this::insert, row, consumer, c ->
            c.map(id).toProperty("id")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(sex).toProperty("sex")
            .map(phoneNum).toProperty("phoneNum")
            .map(email).toProperty("email")
            .map(birth).toProperty("birth")
            .map(introduction).toProperty("introduction")
            .map(location).toProperty("location")
            .map(avator).toProperty("avator")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Consumer> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, consumer, c ->
            c.map(id).toProperty("id")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(sex).toProperty("sex")
            .map(phoneNum).toProperty("phoneNum")
            .map(email).toProperty("email")
            .map(birth).toProperty("birth")
            .map(introduction).toProperty("introduction")
            .map(location).toProperty("location")
            .map(avator).toProperty("avator")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Consumer row) {
        return MyBatis3Utils.insert(this::insert, row, consumer, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
            .map(sex).toPropertyWhenPresent("sex", row::getSex)
            .map(phoneNum).toPropertyWhenPresent("phoneNum", row::getPhoneNum)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(birth).toPropertyWhenPresent("birth", row::getBirth)
            .map(introduction).toPropertyWhenPresent("introduction", row::getIntroduction)
            .map(location).toPropertyWhenPresent("location", row::getLocation)
            .map(avator).toPropertyWhenPresent("avator", row::getAvator)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Consumer> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, consumer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Consumer> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, consumer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Consumer> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, consumer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Consumer> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, consumer, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Consumer row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(username).equalTo(row::getUsername)
                .set(password).equalTo(row::getPassword)
                .set(sex).equalTo(row::getSex)
                .set(phoneNum).equalTo(row::getPhoneNum)
                .set(email).equalTo(row::getEmail)
                .set(birth).equalTo(row::getBirth)
                .set(introduction).equalTo(row::getIntroduction)
                .set(location).equalTo(row::getLocation)
                .set(avator).equalTo(row::getAvator)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateTime).equalTo(row::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Consumer row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(username).equalToWhenPresent(row::getUsername)
                .set(password).equalToWhenPresent(row::getPassword)
                .set(sex).equalToWhenPresent(row::getSex)
                .set(phoneNum).equalToWhenPresent(row::getPhoneNum)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(birth).equalToWhenPresent(row::getBirth)
                .set(introduction).equalToWhenPresent(row::getIntroduction)
                .set(location).equalToWhenPresent(row::getLocation)
                .set(avator).equalToWhenPresent(row::getAvator)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Consumer row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(password).equalTo(row::getPassword)
            .set(sex).equalTo(row::getSex)
            .set(phoneNum).equalTo(row::getPhoneNum)
            .set(email).equalTo(row::getEmail)
            .set(birth).equalTo(row::getBirth)
            .set(introduction).equalTo(row::getIntroduction)
            .set(location).equalTo(row::getLocation)
            .set(avator).equalTo(row::getAvator)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateTime).equalTo(row::getUpdateTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Consumer row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(password).equalToWhenPresent(row::getPassword)
            .set(sex).equalToWhenPresent(row::getSex)
            .set(phoneNum).equalToWhenPresent(row::getPhoneNum)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(birth).equalToWhenPresent(row::getBirth)
            .set(introduction).equalToWhenPresent(row::getIntroduction)
            .set(location).equalToWhenPresent(row::getLocation)
            .set(avator).equalToWhenPresent(row::getAvator)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}