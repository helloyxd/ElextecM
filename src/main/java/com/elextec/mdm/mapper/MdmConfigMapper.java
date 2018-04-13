package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.MdmConfig;

/**
 * @author zhangkj
 *
 */
public interface MdmConfigMapper {

	@Insert("INSERT INTO mdm_config(id,config_name,config_value,remark,status,creater,create_time)"
		    + " VALUES(sys_guid(), #{configName}, #{configValue}, #{remark}, #{status}, #{creater}, sysdate)")
	void insert(MdmConfig entity);
	
	@Delete("DELETE FROM mdm_config WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_config")
    @Results(id = "mapOnly",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "configName", column = "config_name"),
	    @Result(property = "configValue", column = "config_value"),
	    @Result(property = "remark", column = "remark"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	List<MdmConfig> findAll();
	
	@Select("SELECT * FROM mdm_config WHERE config_name = #{configName} ORDER BY create_time desc")
	@ResultMap("mapOnly")
	List<MdmConfig> findByConfigName(String configName);
}
