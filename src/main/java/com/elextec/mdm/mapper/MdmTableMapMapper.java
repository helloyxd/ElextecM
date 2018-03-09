package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.MdmTableMap;

public interface MdmTableMapMapper {

	@Insert("INSERT INTO mdm_table_mapper(id,mdm_table_id,mdm_field_id,bs_table_id,bs_field_id,bs_io_type,status,creater,create_time)"
		    + " VALUES(sys_guid(),#{mdmTableId},#{mdmFieldId},#{bsTableId},#{bsFieldId},#{bsIoType},#{status},#{creater},sysdate)")
	void insert(MdmTableMap entity);
	
	@Delete("DELETE FROM mdm_table_mapper WHERE id = #{id}")
	void del(String id);
	
	@Update("UPDATE mdm_table_mapper SET bs_io_type=#{bsIoType} WHERE id =#{id}")
	void update(MdmTableMap entity);
	
	@Select("SELECT * FROM mdm_table_mapper")
    @Results(id = "mdmTableMapOnly",
    	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "mdmTableId", column = "mdm_table_id"),
	    @Result(property = "mdmFieldId", column = "mdm_field_id"),
	    @Result(property = "bsTableId", column = "bs_table_id"),
	    @Result(property = "bsFieldId", column = "bs_field_id"),
	    @Result(property = "bsIoType", column = "bs_io_type"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	List<MdmTableMap> findAll();
	
	@Select("SELECT * FROM mdm_table_mapper WHERE mdm_table_id = #{tableId}")
	@Results(id = "mdmTableMap",
		value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "mdmTableId", column = "mdm_table_id"),
	    @Result(property = "mdmFieldId", column = "mdm_field_id"),
	    @Result(property = "bsTableId", column = "bs_table_id"),
	    @Result(property = "bsFieldId", column = "bs_field_id"),
	    @Result(property = "bsIoType", column = "bs_io_type"),
	    @Result(property = "tableDefined", column = "mdm_table_id",
			many = @Many(select = "com.elextec.mdm.mapper.TableDefinitionMapper.findById")),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater")
	})
	List<MdmTableMap> findByMdmTableId(String tableId);
	
	@Select("SELECT * FROM mdm_table_mapper WHERE bs_table_id = #{tableId}")
    @ResultMap("mdmTableMapOnly")
	List<MdmTableMap> findByBsTableId(String tableId);
	
	@Select("SELECT * FROM mdm_table_mapper WHERE bs_table_id = '${bstableId}' AND mdm_table_id = '${mdmtableId}'")
    @ResultMap("mdmTableMapOnly")
	List<MdmTableMap> findByTableId(@Param("bstableId")String bstableId, @Param("mdmtableId")String mdmtableId);
	
	@Delete("DELETE FROM mdm_table_mapper WHERE bs_table_id = '${bstableId}' AND mdm_table_id = '${mdmtableId}'")
	void delByTableId(@Param("bstableId")String bstableId, @Param("mdmtableId")String mdmtableId);
}
