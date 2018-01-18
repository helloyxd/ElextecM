package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.Role;

public interface RoleMapper {

    @Select("SELECT * FROM mdm_role WHERE role_name = #{roleName}")
    @Results(id = "roleMap", value = { 
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "roleName", column = "role_name"), 
	    @Result(property = "roleDesc", column = "role_desc"),
	    @Result(property = "status", column = "status"), 
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "menus", column = "id",
	    	many = @Many(select = "com.elextec.mdm.mapper.MenuMapper.findMenusByRoleId") ),
	    @Result(property = "dataPermissions", column = "id",
	    	many = @Many(select = "com.elextec.mdm.mapper.DataPermissionMapper.findDatasByRoleId") ),
    })
    List<Role> findRoleByName(String roleName);

    @Select("SELECT * FROM mdm_role")
    @Results(id = "roleMapOnly", value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "roleName", column = "role_name"), 
	    @Result(property = "roleDesc", column = "role_desc"),
	    @Result(property = "status", column = "status"), 
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") })
    List<Role> findAll();

    @Select("SELECT * FROM mdm_role WHERE id = #{roleId}")
    @ResultMap("roleMap")
    Role findRoleById(String roleId);

    @Insert("INSERT INTO mdm_role(id,role_name,role_desc,status,creater,create_time)"
	    + " VALUES(sys_guid(), #{roleName}, #{roleDesc}, #{status}, #{creater}, sysdate)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Role role);

    @Update("UPDATE mdm_role SET role_name=#{roleName},role_desc=#{roleDesc} WHERE id =#{id}")
    void update(Role role);

    @Delete("DELETE FROM mdm_role WHERE id =#{id}")
    void delete(String id);

    @Update("${sql}")
    void createTable(@Param("sql") String sql);

    @Select("SELECT * FROM mdm_role WHERE id IN (SELECT role_id FROM mdm_user_role WHERE user_id = #{userId})")
    @ResultMap("roleMap")
    List<Role> findRolesByUserId(String userId);

    /**
     * 新增角色的菜单权限信息
     * @param user
     */
    @InsertProvider(type = MapperProvider.class, method = "addRoleMenus")
    void addRoleMenus(@Param("role") Role role);

    /**
     * 根据角色ID，删除角色的菜单信息
     * @param roleId
     */
    @Delete("DELETE FROM mdm_role_menu WHERE role_id=#{roleId}")
    void delRoleMenus(String roleId);
    
    /**
     * 根据角色ID，删除角色的数据权限信息
     * @param roleId
     */
    @Delete("DELETE FROM mdm_role_data WHERE role_id=#{roleId}")
    void delRoleDataPermission(String roleId);
    
    /**
     * 新增角色的数据权限信息
     * @param user
     */
    @InsertProvider(type = MapperProvider.class, method = "addRoleDataPermission")
    void addRoleDataPermission(@Param("role") Role role);
}
