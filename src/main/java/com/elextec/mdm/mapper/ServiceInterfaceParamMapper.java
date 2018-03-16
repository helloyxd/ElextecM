package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.ServiceInterfaceParam;


public interface ServiceInterfaceParamMapper {

	@Select("SELECT * FROM mdm_serviceInterface_param")
    @Results(id = "serviceInterfaceParamMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "serviceDefinedId", column = "service_defined_id"),
		    @Result(property = "ioType", column = "io_type"),
		    @Result(property = "dataType", column = "data_type"),
		    @Result(property = "tableId", column = "table_id"),
		    @Result(property = "tableName", column = "table_name"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ServiceInterfaceParam> findAll();
	
	@Select("SELECT * FROM mdm_serviceInterface_param WHERE service_defined_id = #{siDefinedId}")
    @Results(id = "serviceInterfaceParam",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "serviceDefinedId", column = "service_defined_id"),
		    @Result(property = "ioType", column = "io_type"),
		    @Result(property = "dataType", column = "data_type"),
		    @Result(property = "tableId", column = "table_id"),
		    @Result(property = "tableName", column = "table_name"),
		    @Result(property = "sParamTableDefineds", column = "table_id",
				many = @Many(select = "com.elextec.mdm.mapper.ServiceParamTableDefinedMapper.findById") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ServiceInterfaceParam> findBySIDefinedId(String siDefinedId);
}
