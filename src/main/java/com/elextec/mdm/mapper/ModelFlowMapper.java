package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.ModelFlow;

public interface ModelFlowMapper {

	@Insert("INSERT INTO mdm_model_flow(id,activiti_id,model_id,activiti_model_id,operation_type,status,creater,create_time) "
			+ "VALUES(sys_guid(),#{activitiId,jdbcType=VARCHAR},#{modelId},#{activitiModelId,jdbcType=VARCHAR},#{operationType},#{status},#{creater,jdbcType=VARCHAR},sysdate)")
	void insert(ModelFlow entity);
	
	@Delete("DELETE FROM mdm_model_flow WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE mdm_model_flow SET status=#{status} WHERE id =#{id}")
	void update(ModelFlow entity);
	
	@Select("SELECT * FROM mdm_model_flow")
    @Results(id = "modelFlowMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "activitiId", column = "activiti_id"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "activitiModelId", column = "activiti_model_id"),
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
	    @Result(property = "modelId", column = "model_id"),
	    @Result(property = "activitiModelId", column = "activiti_model_id"),
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
