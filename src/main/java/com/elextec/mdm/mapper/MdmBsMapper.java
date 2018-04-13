package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.MdmBs;

/**
 * @author zhangkj
 *
 */
public interface MdmBsMapper {

	@Insert("INSERT INTO mdm_bs(id,bs_name,status,creater,create_time)"
		    + " VALUES(sys_guid(), #{bsName}, #{status}, #{creater}, sysdate)")
	void insert(MdmBs bs);
	
	@Delete("DELETE FROM mdm_bs WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_bs")
    @Results(id = "bsMapOnly",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "bsName", column = "bs_name"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	List<MdmBs> findAll();
	
	@Select("SELECT * FROM mdm_bs WHERE bs_name = #{name}")
	@ResultMap("bsMapOnly")
	List<MdmBs> findByName(String name);
	
	@Select("SELECT bs_name FROM mdm_bs WHERE id = #{id}")
	@ResultType(String.class) 
	String findBsNameById(String id);
	
	@Select("SELECT * FROM mdm_bs WHERE id = #{id}")
	@Results(id = "bsMap",
		value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "bsName", column = "bs_name"),
	    @Result(property = "siDefineds", column = "id",
			many = @Many(select = "com.elextec.mdm.mapper.ServiceInterfaceDefinedMapper.findByBsId") ),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	MdmBs findById(String id);
	
	@Select("SELECT * FROM mdm_bs WHERE id = #{id}")
	@ResultMap("bsMapOnly")
	MdmBs findByIdOnly(String id);
}
