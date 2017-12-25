package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.TableRelation;

/**
 * @author zhangkj
 *
 */
public interface TableRelationMapper {

	@Insert("INSERT INTO mdm_tablerelation(id,table1,table2,relation,foreign_key1,foreign_key2,muti_relation_table,creater,create_time)"
		    + " VALUES(sys_guid(), #{bsName}, #{status}, #{creater}, sysdate)")
	void insert(TableRelation tableRelation);
	
	@Delete("DELETE FROM mdm_tablerelation WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_tablerelation")
    @Results(id = "tableRelationMap",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "bsName", column = "bs_name"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	List<TableRelation> findAll();
}
