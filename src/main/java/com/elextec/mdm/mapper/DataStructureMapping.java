package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.DataStructureMap;

/**
 * @author zhangkj
 *
 */
public interface DataStructureMapping {

	@Insert("INSERT INTO mdm_datastructure_mapping(id,table_id,interface_id,field1,field2,status,creater,create_time)"
		    + " VALUES(sys_guid(),#{tableId},#{interfaceId},#{field1},#{field2},#{status},#{creater},sysdate)")
	void insert(DataStructureMap entity);
	
	@Delete("DELETE FROM mdm_datastructure_mapping WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE mdm_datastructure_mapping SET full_name=#{fullName},user_password=#{userPassword},"
		    + "department_id=#{department.id,jdbcType=VARCHAR},status=#{status} WHERE id =#{id}")
	void update(DataStructureMap entity);
	
	@Select("SELECT * FROM mdm_datastructure_mapping")
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
	List<DataStructureMap> findAll();
	
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
	List<DataStructureMap> findByTableId(String tableId);
	
}
