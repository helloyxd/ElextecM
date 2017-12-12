package com.elextec.mdm.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.MdmModel;

public interface MdmModelMapper {

	@Select("SELECT * FROM mdm_model WHERE id = #{id}")
	@Results(id="modelMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "mdmModel",  column = "mdm_model"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
        @Result(property = "creater", column = "creater")
	})
	MdmModel findById(int id);
	
	
	
}
