package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.elextec.mdm.entity.DataPermissionDefined;

/**
 * @author zhangkj
 *
 */
public interface DataPermissionDefinedMapper {
    
    @Select("SELECT * FROM mdm_datapermission_defined")
    @Results(id = "datapermissionDefineMap",
    	value = {
    	    @Result(id = true, property = "id", column = "id"),
    	    @Result(property = "tableId", column = "table_id"),
    	    @Result(property = "tableDefinition", column = "table_id",
    		many = @Many(select = "com.elextec.mdm.mapper.TableDefinitionMapper.findById")),
    	    @Result(property = "permissionField", column = "permission_field"),
    	    @Result(property = "dataPermission", column = "id",
        		many = @Many(select = "com.elextec.mdm.mapper.DataPermissionMapper.findByDefinedId")),
    	    @Result(property = "status", column = "status"),
    	    @Result(property = "createTime", column = "create_time"), 
    	    @Result(property = "creater", column = "creater")
    	})
    List<DataPermissionDefined> findAll();
    
    @Insert("INSERT INTO mdm_datapermission_defined(id,table_id,permission_field,status,creater,create_time)"
		    + " VALUES(sys_guid(), #{tableId}, #{permissionField}, #{status}, #{creater}, sysdate)")
	void insert(DataPermissionDefined dataPermissionDefined);
	
	@Delete("DELETE FROM mdm_datapermission_defined WHERE id = #{id}")
	void del(String id);
	
	@Select("SELECT * FROM mdm_datapermission_defined WHERE id = #{id}")
	@ResultMap("datapermissionDefineMap")
	DataPermissionDefined findById(String id);
	
	@Select("SELECT * FROM mdm_datapermission_defined WHERE table_id = #{tableId} AND permission_field = #{permissionField}")
	@ResultMap("datapermissionDefineMap")
	DataPermissionDefined findByName(@Param("tableId")String tableId, @Param("permissionField")String permissionField);
	
	@InsertProvider(type = MapperProvider.class, method = "addDataPermissions")
    void addDataPermissions(@Param("dataPermissionDefined") DataPermissionDefined entity);
}
