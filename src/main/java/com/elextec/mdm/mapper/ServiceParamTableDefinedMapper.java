package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.ServiceParamTableDefined;

public interface ServiceParamTableDefinedMapper {

	@Select("SELECT * FROM mdm_serviceParam_TableDefined")
    @Results(id = "serviceParamTableDefinedMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "paramId", column = "param_id"),
		    @Result(property = "tableName", column = "table_name"),
		    @Result(property = "parentId", column = "parent_id"),
		    @Result(property = "relationType", column = "relation_type"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ServiceParamTableDefined> findAll();
	
	@Select("SELECT * FROM mdm_serviceParam_TableDefined WHERE id = #{id}")
	@Results(id = "serviceParamTableDefinedMap",
	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "paramId", column = "param_id"),
	    @Result(property = "tableName", column = "table_name"),
	    @Result(property = "parentId", column = "parent_id"),
	    @Result(property = "relationType", column = "relation_type"),
	    @Result(property = "sParamFieldDefineds", column = "id",
			many = @Many(select = "com.elextec.mdm.mapper.ServiceParamFieldDefinedMapper.findByTableId") ),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	ServiceParamTableDefined findById(String id);
	
	@Select("SELECT * FROM mdm_serviceParam_TableDefined WHERE param_id = #{paramId}")
	@ResultMap("serviceParamTableDefinedMap")
	ServiceParamTableDefined findByParamId(String paramId);
}
