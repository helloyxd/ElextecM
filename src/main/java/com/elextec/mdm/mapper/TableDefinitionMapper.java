package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.TableDefinition;

/**
 * @author zhangkj
 *
 */
public interface TableDefinitionMapper {
	
	@Insert("INSERT INTO mdm_tabledefinition(id,table_name,table_label,model_id,status,creater,create_time)"
		    + " VALUES(sys_guid(), #{tableName}, #{tableLabel}, #{modelId}, #{status}, #{creater}, sysdate)")
	void insert(TableDefinition table);
	
	@Delete("DELETE FROM mdm_tabledefinition WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_tabledefinition")
    @Results(id = "tableDefinitionMap",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "tableName", column = "table_name"),
	    @Result(property = "tableLabel", column = "table_label"),
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	List<TableDefinition> findAll();
	
	@Select("SELECT * FROM mdm_tabledefinition where id = #{id}")
	@ResultMap("tableDefinitionMap")
	TableDefinition findById(String id);
	
}
