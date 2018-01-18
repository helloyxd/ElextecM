package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.TableRelation;

/**
 * @author zhangkj
 *
 */
public interface TableRelationMapper {

	@Insert("INSERT INTO mdm_tablerelation(id,table1,table2,relation,foreign_key1,foreign_key2,"
			+ "muti_relation_table,status,creater,create_time)"
		    + " VALUES(sys_guid(), #{table1}, #{table2}, #{relation}, #{foreignKey1}, #{foreignKey2},"
		    + " #{mutiRelationTable}, #{status}, #{creater}, sysdate)")
	void insert(TableRelation tableRelation);
	
	@Delete("DELETE FROM mdm_tablerelation WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_tablerelation")
    @Results(id = "tableRelationMap",
    	value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "table1", column = "table1"),
	    @Result(property = "tableDefinition1", column = "table1",
    		one = @One(select = "com.elextec.mdm.mapper.TableDefinitionMapper.findById") ),
	    @Result(property = "table2", column = "table2"),
	    @Result(property = "tableDefinition2", column = "table2",
			one = @One(select = "com.elextec.mdm.mapper.TableDefinitionMapper.findById") ),
	    @Result(property = "relation", column = "relation"),
	    @Result(property = "foreignKey1", column = "foreign_key1"),
	    @Result(property = "foreignKey2", column = "foreign_key2"),
	    @Result(property = "mutiRelationTable", column = "muti_relation_table"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") 
	})
	List<TableRelation> findAll();
	
	@Select("SELECT * FROM mdm_tablerelation WHERE table1 = #{tableId}")
	List<TableRelation> findByTableId(String tableId);
}
