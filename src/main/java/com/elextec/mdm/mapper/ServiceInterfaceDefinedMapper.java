package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.ServiceInterfaceDefined;

public interface ServiceInterfaceDefinedMapper {

	@Select("SELECT * FROM mdm_serviceInterface_defined")
    @Results(id = "serviceInterfaceDefinedMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "type", column = "type"),
		    @Result(property = "wsdlLocation", column = "wsdl_location"),
		    @Result(property = "dburl", column = "dburl"),
		    @Result(property = "username", column = "user_name"),
		    @Result(property = "password", column = "password"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "bsId", column = "bs_id"),
		    @Result(property = "operationType", column = "operation_type"),
		    @Result(property = "operation", column = "operation"),
		    @Result(property = "wsbinding", column = "wsbinding"),
		    @Result(property = "bingNamespace", column = "bing_namespace"),
		    @Result(property = "operationNamespace", column = "operation_namespace"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ServiceInterfaceDefined> findAll();
	
	@Select("SELECT * FROM mdm_serviceInterface_defined where model_id = #{modelId}")
	@ResultMap("serviceInterfaceDefinedMapOnly")
	List<ServiceInterfaceDefined> findByModelId();
	
	@Select("SELECT * FROM mdm_serviceInterface_defined where bs_id = #{modelId}")
	@ResultMap("serviceInterfaceDefinedMapOnly")
	List<ServiceInterfaceDefined> findByBsId();
	
	@Select("SELECT * FROM mdm_serviceInterface_defined WHERE id = #{id}")
    @Results(id = "serviceInterfaceDefined",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "type", column = "type"),
		    @Result(property = "wsdlLocation", column = "wsdl_location"),
		    @Result(property = "dburl", column = "dburl"),
		    @Result(property = "username", column = "user_name"),
		    @Result(property = "password", column = "password"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "bsId", column = "bs_id"),
		    @Result(property = "operationType", column = "operation_type"),
		    @Result(property = "operation", column = "operation"),
		    @Result(property = "wsbinding", column = "wsbinding"),
		    @Result(property = "bingNamespace", column = "bing_namespace"),
		    @Result(property = "operationNamespace", column = "operation_namespace"),
		    @Result(property = "siParams", column = "id",
    			many = @Many(select = "com.elextec.mdm.mapper.ServiceInterfaceParamMapper.findBySIDefinedId") ),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	ServiceInterfaceDefined findById(String id);
}
