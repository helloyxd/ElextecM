package com.elextec.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TableDDLMapper {
	
	@Update("${createSql}")
	public void createTable(@Param("createSql") String createSql);
	
	@Update("${dropSql}")
	public void dropTable(@Param("dropSql") String dropSql);
	
	@Update("${alterSql}")
	public void alterTable(@Param("alterSql") String alterSql);
	
	@Select("SELECT count(*) FROM ${tableName}")
	@ResultType(Integer.class)
	public int queryTable(@Param("tableName") String tableName);
	
	@Select("SELECT count(*) FROM user_tables WHERE table_name = #{tableName}")
	@ResultType(Integer.class)
	public int queryTableName(@Param("tableName") String tableName);
	
	@Select("SELECT count(*) FROM user_tab_columns WHERE table_name = #{tableName} AND column_name = #{columnName}")
	@ResultType(Integer.class)
	public int queryColumnName(@Param("tableName") String tableName, @Param("columnName") String columnName);
	
	@Select("SELECT table_name,comments FROM user_col_comments WHERE table_name=#{tableName}")
    @ResultType(List.class)
	List<Map<String, String>> getTableCommentsDefine(@Param("tableName") String tableName);
	
	@Select("SELECT column_name,comments FROM user_col_comments WHERE table_name=#{tableName}")
    @ResultType(List.class)
	List<Map<String, String>> getColumnCommentsDefine(@Param("tableName") String tableName);
	
	@Select("SELECT column_name,data_type,data_length,nullable,data_precision,data_scale "
			+ "FROM user_tab_columns WHERE table_Name=#{tableName}")
    @ResultType(List.class)
	List<Map<String, String>> getTableColumnsDefine(@Param("tableName") String tableName);
	
	
}
