package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.ModelFlow;

public interface ModelFlowMapper {

	@Select("SELECT * FROM mdm_model_flow")
    @Results(id = "modelFlowMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "activitiId", column = "activiti_id"),
		    @Result(property = "siId", column = "model_id"),
		    @Result(property = "operationType", column = "operation_type"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<ModelFlow> findAll();
	
	@Select("SELECT * FROM mdm_model_flow where model_id = #{modelId}")
	@ResultMap("modelFlowMap")
	List<ModelFlow> findByModelId(String modelId);
	
	@Select("SELECT * FROM mdm_model_flow where activiti_id = #{activitiId}")
	@ResultMap("modelFlowMap")
	List<ModelFlow> findByActivitiId(String activitiId);
	
	@Select("SELECT * FROM mdm_model_flow WHERE id = #{id}")
	@Results(id = "modelFlowMap",
	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "activitiId", column = "activiti_id"),
	    @Result(property = "siId", column = "model_id"),
	    @Result(property = "operationType", column = "operation_type"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})	
	ModelFlow findById(String id);
	
	@Select("SELECT * FROM mdm_model_flow where model_id = #{modelId} AND operation_type = #{operationType}")
	@ResultMap("modelFlowMap")
	ModelFlow findByModelIdAndType(@Param("modelId")String modelId, @Param("operationType")String operationType);
}
