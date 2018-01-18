package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.QueryFieldDefined;

/**
 * @author zhangkj
 *
 */
public interface QueryFieldDefinedMapper {

	@Insert("INSERT INTO mdm_queryfield_defined(id,table_id,field,field_type,sort_order,column_span,status,creater,create_time)"
		    + " VALUES(sys_guid(),#{tableId},#{field},#{fieldType},#{sortOrder},#{column_span},#{status},#{creater},sysdate)")
	void insert(QueryFieldDefined entity);
	
	@Delete("DELETE FROM mdm_queryfield_defined WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE mdm_queryfield_defined SET full_name=#{fullName},user_password=#{userPassword},"
		    + "department_id=#{department.id,jdbcType=VARCHAR},status=#{status} WHERE id =#{id}")
	void update(QueryFieldDefined entity);
	
	@Select("SELECT * FROM mdm_queryfield_defined")
    @Results(id = "queryFieldMap",
    	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "tableId", column = "table_id"),
	    @Result(property = "field", column = "field"),
	    @Result(property = "fieldType", column = "field_type"),
	    @Result(property = "sortOrder", column = "sort_order"),
	    @Result(property = "columnSpan", column = "column_span"),
	    @Result(property = "tableDefined", column = "table_id",
			many = @Many(select = "com.elextec.mdm.mapper.TableDefinitionMapper.findById")),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	List<QueryFieldDefined> findAll();
	
	@Select("SELECT * FROM mdm_queryfield_defined WHERE table_id = #{tableId}")
	@Results(id = "queryFieldMapOnly",
	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "tableId", column = "table_id"),
	    @Result(property = "field", column = "field"),
	    @Result(property = "fieldType", column = "field_type"),
	    @Result(property = "sortOrder", column = "sort_order"),
	    @Result(property = "columnSpan", column = "column_span"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	List<QueryFieldDefined> findByTableId(String tableId);
	
	
}
