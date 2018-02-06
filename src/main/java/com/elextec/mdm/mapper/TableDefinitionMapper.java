package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
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
	
	@Insert("INSERT INTO mdm_tabledefinition(id,table_name,table_label,model_id,isMenu,status,creater,create_time)"
		    + " VALUES(sys_guid(), #{tableName}, #{tableLabel}, #{modelId}, #{isMenu,jdbcType=BIT}, #{status}, #{creater}, sysdate)")
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
	    @Result(property = "isMenu", column = "isMenu"),
	    /*@Result(property = "model", column = "model_id",
			one = @One(select = "com.elextec.mdm.mapper.MdmModelMapper.findById") ),*/
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	List<TableDefinition> findAll();
	
	@Select("SELECT * FROM mdm_tabledefinition where id = #{id}")
	@ResultMap("tableDefinitionMap")
	TableDefinition findById(String id);
	
	@Select("SELECT * FROM mdm_tabledefinition where model_id = #{modelId}")
	@ResultMap("tableDefinitionMap")
	List<TableDefinition> findByModelId(String modelId);
	
	@Select("SELECT * FROM mdm_tabledefinition where model_id = #{id} AND table_name = #{name}")
	@ResultMap("tableDefinitionMap")
	List<TableDefinition> findByModelIdAndName(@Param("id")String modelId, @Param("name")String tableName);
	
	
}
