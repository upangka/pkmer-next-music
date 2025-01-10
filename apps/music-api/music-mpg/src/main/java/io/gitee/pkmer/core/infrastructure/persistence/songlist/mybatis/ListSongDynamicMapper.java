package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import static io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.ListSongDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.ListSong;
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
public interface ListSongDynamicMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ListSong>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, songId, songListId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ListSongResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="song_id", property="songId", jdbcType=JdbcType.BIGINT),
        @Result(column="song_list_id", property="songListId", jdbcType=JdbcType.BIGINT)
    })
    List<ListSong> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ListSongResult")
    Optional<ListSong> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, listSong, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, listSong, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(ListSong row) {
        return MyBatis3Utils.insert(this::insert, row, listSong, c ->
            c.map(id).toProperty("id")
            .map(songId).toProperty("songId")
            .map(songListId).toProperty("songListId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<ListSong> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, listSong, c ->
            c.map(id).toProperty("id")
            .map(songId).toProperty("songId")
            .map(songListId).toProperty("songListId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(ListSong row) {
        return MyBatis3Utils.insert(this::insert, row, listSong, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(songId).toPropertyWhenPresent("songId", row::getSongId)
            .map(songListId).toPropertyWhenPresent("songListId", row::getSongListId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<ListSong> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, listSong, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<ListSong> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, listSong, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<ListSong> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, listSong, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<ListSong> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, listSong, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(ListSong row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(songId).equalTo(row::getSongId)
                .set(songListId).equalTo(row::getSongListId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ListSong row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(songId).equalToWhenPresent(row::getSongId)
                .set(songListId).equalToWhenPresent(row::getSongListId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(ListSong row) {
        return update(c ->
            c.set(songId).equalTo(row::getSongId)
            .set(songListId).equalTo(row::getSongListId)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(ListSong row) {
        return update(c ->
            c.set(songId).equalToWhenPresent(row::getSongId)
            .set(songListId).equalToWhenPresent(row::getSongListId)
            .where(id, isEqualTo(row::getId))
        );
    }
}