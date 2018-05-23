package com.elextec.bi.mapper;

import com.elextec.bi.entity.BiDataSource;
import com.elextec.bi.entity.BiReportColConf;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BiDataSourceMapper {

    @Select("SELECT ID,SOURCE_NAME,SOURCE_TYPE,CONFIG FROM BI_DATASOURCE WHERE ID = #{id}")
    @Results(id = "biDataSourceMap", value = {
        @Result(id = true, property = "id", column = "ID"),
	    @Result(property = "sourceName", column = "SOURCE_NAME"),
	    @Result(property = "sourceType", column = "SOURCE_TYPE"),
        @Result(property = "config", column = "CONFIG")
    })
    BiDataSource queryById(String id);

    @Select("SELECT ID,SOURCE_NAME,SOURCE_TYPE,CONFIG FROM BI_DATASOURCE WHERE SOURCE_NAME = #{sourceName}")
    @ResultMap("biDataSourceMap")
    BiDataSource queryBySourceName(String sourceName);

    @Select("SELECT ID,SOURCE_NAME,SOURCE_TYPE,CONFIG FROM BI_DATASOURCE")
    @ResultMap("biDataSourceMap")
    List<BiDataSource> queryAll();

    @Insert("INSERT INTO BI_DATASOURCE(ID,SOURCE_NAME,SOURCE_TYPE,CONFIG,STATUS,CREATER,CREATE_TIME)"
            + " VALUES(sys_guid(), #{sourceName}, #{sourceType}, #{config,jdbcType=VARCHAR}, "
            + "#{status}, #{creater}, sysdate)")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    @ResultMap("biUserMap")
    void insert(BiDataSource biDataSource);

    @Update("UPDATE BI_DATASOURCE SET SOURCE_NAME=#{sourceName},SOURCE_TYPE=#{sourceType},"
            + "CONFIG=#{config,jdbcType=VARCHAR},STATUS=#{status} WHERE ID =#{id}")
    void update(BiDataSource biDataSource);

    @Delete("DELETE FROM BI_DATASOURCE WHERE ID =#{id}")
    void delete(String id);

}
