package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.DataPermission;

/**
 * @author zhangkj
 *
 */
public interface DataPermissionMapper {

    @Select("SELECT * FROM mdm_datapermission")
    @Results(id = "datapermissionMap",
	value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "definedId", column = "defined_id"),
	    @Result(property = "roleId", column = "role_id"),
	    @Result(property = "permissionValue", column = "permission_value"),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"), 
	    @Result(property = "creater", column = "creater")
	})
    List<DataPermission> findAll();
    
    @Select("SELECT * FROM mdm_datapermission WHERE role_id = #{roleId}")
    @ResultMap("datapermissionMap")
    List<DataPermission> findByRoleId(String roleId);
    
    @Insert("INSERT INTO mdm_datapermission(id,defined_id,role_id,permission_value,status,creater,create_time)"
	    + " VALUES(sys_guid(), #{definedId}, #{roleId}, #{permissionValue}, #{status}, #{creater}, sysdate)")
    void insert(DataPermission dataPermission);
    
    @Delete("DELETE FROM mdm_datapermission WHERE id =#{id}")
    void del(String id);
    
    @Update("UPDATE mdm_datapermission SET permission_value=#{permissionValue} WHERE id =#{id}")
    void update(DataPermission dataPermission);
}
