package com.elextec.mdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.UserEntity;

public interface TableDLLMapper {
	
	@Update("${createSql}")
	public void createTable(@Param("createSql") String createSql);
	
	@Update("${dropSql}")
	public void dropTable(@Param("dropSql") String dropSql);
	
	@Update("${alterSql}")
	public void alterTable(@Param("alterSql") String alterSql);
	
	
    List<Map> getDBDataType();
	
	
	@Select("select COLUMN_NAME,COLUMN_TYPE from information_schema.columns where TABLE_NAME=#{tableName}")
    @Results({
        @Result(property = "columnName",  column = "COLUMN_NAME"),
        @Result(property = "columnType",  column = "COLUMN_TYPE")
    })
	List<Map> getTableColumnDefine(@Param("tableName") String tableName);
}
