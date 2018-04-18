package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.MdmDataMap;

public interface MdmDataMapMapper {

	@Insert("INSERT INTO mdm_data_mapper(id,mdm_data_id,bs_data_id,model_id,bs_id,modifier,modifier_time,status,creater,create_time)"
		    + " VALUES(sys_guid(),#{mdmDataId},#{bsDataId},#{modelId},#{bsId},#{modifier,jdbcType=VARCHAR},#{modifierTime,jdbcType=TIMESTAMP},#{status},#{creater},sysdate)")
	void insert(MdmDataMap entity);
	
	@Delete("DELETE FROM mdm_data_mapper WHERE id = #{id}")
	void del(String id);
	
	@Delete("DELETE FROM mdm_data_mapper WHERE model_id = #{modelId} AND bs_id = #{bsId}")
	void delByModelIdAndBsId(String modelId,String bsId);
	
	@Update("UPDATE mdm_data_mapper SET bs_data_id=#{bsDataId},modifier=#{modifier,jdbcType=VARCHAR},modifier_time=#{modifierTime,jdbcType=TIMESTAMP} WHERE id =#{id}")
	void update(MdmDataMap entity);
	
	@Select("SELECT * FROM mdm_data_mapper")
    @Results(id = "mdmDataMapOnly",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "mdmDataId", column = "mdm_data_id"),
		    @Result(property = "bsDataId", column = "bs_data_id"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "bsId", column = "bs_id"),
		    @Result(property = "modifier", column = "modifier"),
		    @Result(property = "modifierTime", column = "modifier_time"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<MdmDataMap> findAll();
	
	@Select("SELECT * FROM mdm_data_mapper WHERE model_id = #{modelId} AND bs_id = #{bsId}")
	@Results(id = "mdmDataMap",
    	value = {
		    @Result(id = true, property = "id", column = "id"),
		    @Result(property = "mdmDataId", column = "mdm_data_id"),
		    @Result(property = "bsDataId", column = "bs_data_id"),
		    @Result(property = "modelId", column = "model_id"),
		    @Result(property = "bsId", column = "bs_id"),
		    @Result(property = "modifier", column = "modifier"),
		    @Result(property = "modifierTime", column = "modifier_time"),
		    @Result(property = "status", column = "status"),
		    @Result(property = "createTime", column = "create_time"),
		    @Result(property = "creater", column = "creater")
	})
	List<MdmDataMap> findByMdmIdAndBsId(String modelId,String bsId);
	
	@Select("SELECT * FROM mdm_data_mapper WHERE model_id = #{modelId} AND bs_id = #{bsId} AND mdm_data_id=#{mdmDataId}")
	@ResultMap("mdmDataMapOnly")
	MdmDataMap findByMdmId(MdmDataMap entity);
}
