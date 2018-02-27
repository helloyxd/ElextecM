package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.ServiceParamFieldDefined;

public interface ServiceParamFieldDefinedMapper {

	@Select("SELECT * FROM mdm_serviceParam_FieldDefined")
    @Results(id = "serviceParamFieldDefined",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "tableId", column = "table_id"),
		    @Result(property = "fieldName", column = "field_name"),
		    @Result(property = "fieldType", column = "field_type"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ServiceParamFieldDefined> findAll();
	
	@Select("SELECT * FROM mdm_serviceParam_FieldDefined WHERE table_id = #{tableId}")
	@ResultMap("serviceParamFieldDefined")
	List<ServiceParamFieldDefined> findByTableId(String tableId);
}
