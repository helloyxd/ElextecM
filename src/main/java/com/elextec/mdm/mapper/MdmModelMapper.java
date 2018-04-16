package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import com.elextec.mdm.entity.MdmModel;

public interface MdmModelMapper extends BaseMapper<MdmModel>{

	@Insert("INSERT INTO mdm_model(id,mdm_model,status,creater,create_time)"
		    + " VALUES(#{id}, #{mdmModel}, #{status}, #{creater}, sysdate)")
	@SelectKey(before = true, keyProperty = "id",
		resultType = String.class, statementType = StatementType.STATEMENT,
		statement="SELECT sys_guid() FROM dual")
	void insert(MdmModel model);
	
	@Delete("DELETE FROM mdm_model WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_model")
    @Results(id = "modelMap",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "mdmModel", column = "mdm_model"),
	    @Result(property = "tableDefinitions", column = "id",
    		many = @Many(select = "com.elextec.mdm.mapper.TableDefinitionMapper.findByModelId") ),
	 /*   @Result(property = "siDefineds", column = "id",
			many = @Many(select = "com.elextec.mdm.mapper.ServiceInterfaceDefinedMapper.findByModelId") ),*/
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	List<MdmModel> findAllInfo();
	
	@Select("SELECT * FROM mdm_model")
	@Results(id = "modelMapOnly",
	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "mdmModel", column = "mdm_model"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	List<MdmModel>  findAll();
	
	@Select("SELECT * FROM mdm_model WHERE id = #{id}")
	@ResultMap("modelMap")
	MdmModel findById(String id);
	
	@Select("SELECT * FROM mdm_model WHERE id = #{id}")
	@ResultMap("modelMapOnly")
	MdmModel findByIdOnly(String id);
	
	@Select("SELECT * FROM mdm_model WHERE mdm_model = #{name}")
	@ResultMap("modelMap")
	List<MdmModel> findByName(String name);
	
}
