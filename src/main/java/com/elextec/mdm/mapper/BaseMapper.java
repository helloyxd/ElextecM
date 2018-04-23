package com.elextec.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.elextec.mdm.common.entity.PageQuery;


public interface BaseMapper<T> {

	@SelectProvider(type = MapperProvider.class, method = "findEntityCount")
    int findCount(@Param("conditions")String conditions, @Param("queryParam") Map<String,String> map, @Param("tableName") String tableName);
	
	@SelectProvider(type = MapperProvider.class, method = "findEntityByPage")
    List<T> findByPage(@Param("conditions")String conditions, @Param("queryParam") Map<String,String> map, @Param("page") PageQuery pageQuery);
	
	@Select("SELECT id FROM ${tableName} WHERE ${queryField} LIKE '%${queryParam}%'")
	@ResultType(List.class)
	List<T> queryByName(@Param("tableName") String tableName,@Param("queryField") String queryField, @Param("queryParam") String queryParam);
}
