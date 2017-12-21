package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.DataPermissionDefined;

/**
 * @author zhangkj
 *
 */
public interface DataPermissionDefinedMapper {
    
    @Select("SELECT * FROM mdm_datapermission_defined")
    @Results(id = "datapermission_define",
    	value = {
    	    @Result(id = true, property = "id", column = "id"),
    	    
    	    @Result(property = "status", column = "status"),
    	    @Result(property = "createTime", column = "create_time"), 
    	    @Result(property = "creater", column = "creater")
    	})
    List<DataPermissionDefined> findAll();
    
    
}
