package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
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
	    @Result(property = "permissionValue", column = "permission_value"),
	    @Result(property = "dataPermissionDefined", column = "defined_id",
			many = @Many(select = "com.elextec.mdm.mapper.DataPermissionDefinedMapper.findByIdOnly")),
	    @Result(property = "status", column = "status"),
	    @Result(property = "createTime", column = "create_time"), 
	    @Result(property = "creater", column = "creater")
	})
    List<DataPermission> findAll();
    
    /**
	 * 根据UserID和tableId查询用户的数据权限信息
	 * @return
	 */
	@Select("SELECT * FROM mdm_datapermission "
			+ "WHERE defined_id IN (SELECT id FROM mdm_datapermission_defined WHERE table_id = #{tableId}) "
			+ "AND id IN (SELECT data_id FROM mdm_role_data "
			+ "WHERE role_id IN (SELECT role_id FROM mdm_user_role WHERE user_id = #{userId}))")
	@ResultMap("datapermissionMap")
    List<DataPermission> findDatasByUserIdAndTable(@Param("userId")String userId, @Param("tableId")String tableId);
	
	/**
	 * 根据UserID和dataDefinedId查询用户的数据权限信息
	 * @return
	 */
	@Select("SELECT * FROM mdm_datapermission "
			+ "WHERE defined_id = #{definedId} "
			+ "AND id IN (SELECT data_id FROM mdm_role_data "
			+ "WHERE role_id IN (SELECT role_id FROM mdm_user_role WHERE user_id = #{userId}))")
	@ResultMap("datapermissionMap")
    List<DataPermission> findDatasByUserIdAndDataDefined(@Param("userId")String userId, @Param("definedId")String dataDefinedId);
    
    /**
	 * 根据角色ID查询角色的数据权限信息
	 * @param roleId
	 * @return
	 */
	@Select("SELECT * FROM mdm_datapermission WHERE id IN (SELECT data_id FROM mdm_role_data WHERE role_id = #{roleId})")
	@ResultMap("datapermissionMap")
    List<DataPermission> findDatasByRoleId(String roleId);
    
    @Insert("INSERT INTO mdm_datapermission(id,defined_id,permission_value,status,creater,create_time)"
	    + " VALUES(sys_guid(), #{definedId}, #{permissionValue}, #{status}, #{creater}, sysdate)")
    void insert(DataPermission dataPermission);
    
    @Delete("DELETE FROM mdm_datapermission WHERE id =#{id}")
    void del(String id);
    
    @DeleteProvider(type = MapperProvider.class, method = "delAll")
    void delAll(@Param("tableName")String tableName, @Param("ids")List<String> ids);
    
    @Update("UPDATE mdm_datapermission SET permission_value=#{permissionValue} WHERE id =#{id}")
    void update(DataPermission dataPermission);
    
    @Select("SELECT * FROM mdm_datapermission WHERE defined_id=#{definedId}")
    @ResultMap("datapermissionMap")
    List<DataPermission> findByDefinedId(String definedId);
    
    @Select("SELECT * FROM mdm_datapermission WHERE id=#{id}")
    @ResultMap("datapermissionMap")
    DataPermission findById(String id);
    
}
