package com.elextec.bi.mapper;

import com.elextec.bi.entity.BiRole;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.mapper.MapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BiRoleMapper {

    @Select("SELECT * FROM bi_role WHERE role_name = #{roleName}")
    @Results(id = "biRoleMap", value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "roleName", column = "role_name"), 
	    @Result(property = "roleDesc", column = "role_desc"),
        @Result(property = "roleDataPermissions", column = "ROLE_DATAPERMISSION"),
	    @Result(property = "status", column = "status"), 
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater"),
	    @Result(property = "menus", column = "id",
	    	many = @Many(select = "com.elextec.bi.mapper.BiMenuMapper.findMenusByRoleId") ),
//	    @Result(property = "dataPermissions", column = "id",
//	    	many = @Many(select = "com.elextec.mdm.mapper.DataPermissionMapper.findDatasByRoleId") ),
    })
    List<BiRole> findRoleByName(String roleName);

    @Select("SELECT * FROM bi_role")
    @Results(id = "biRoleMapOnly", value = {
	    @Result(id = true, property = "id", column = "id"),
	    @Result(property = "roleName", column = "role_name"), 
	    @Result(property = "roleDesc", column = "role_desc"),
        @Result(property = "roleDataPermissions", column = "ROLE_DATAPERMISSION"),
	    @Result(property = "status", column = "status"), 
	    @Result(property = "createTime", column = "create_time"),
	    @Result(property = "creater", column = "creater") })
    List<BiRole> findAll();

    @Select("SELECT * FROM bi_role WHERE id = #{roleId}")
    @ResultMap("biRoleMap")
    BiRole findRoleById(String roleId);

    @Insert("INSERT INTO bi_role(id,role_name,role_desc,ROLE_DATAPERMISSION,status,creater,create_time)"
	    + " VALUES(sys_guid(), #{roleName}, #{roleDesc,jdbcType=VARCHAR},ROLE_DATAPERMISSION=#{roleDataPermissions,jdbcType=VARCHAR}, #{status}, #{creater}, sysdate)")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(BiRole role);

    @Update("UPDATE bi_role SET role_name=#{roleName},role_desc=#{roleDesc,jdbcType=VARCHAR},ROLE_DATAPERMISSION=#{roleDataPermissions,jdbcType=VARCHAR} WHERE id =#{id}")
    void update(BiRole role);

    @Delete("DELETE FROM bi_role WHERE id =#{id}")
    void delete(String id);

//    @Update("${sql}")
//    void createTable(@Param("sql") String sql);

    @Select("SELECT * FROM bi_role WHERE id IN (SELECT role_id FROM bi_user_role WHERE user_id = #{userId})")
    @ResultMap("biRoleMap")
    List<BiRole> findRolesByUserId(String userId);

    /**
     * 新增角色的菜单权限信息
     * @param role
     */
    @InsertProvider(type = MapperProvider.class, method = "addRoleMenus")
    void addRoleMenus(@Param("role") BiRole role);

    /**
     * 根据角色ID，删除角色的菜单信息
     * @param roleId
     */
    @Delete("DELETE FROM bi_role_menu WHERE role_id=#{roleId}")
    void delRoleMenus(String roleId);
    
//    /**
//     * 根据角色ID，删除角色的数据权限信息
//     * @param roleId
//     */
//    @Delete("DELETE FROM bi_role_data WHERE role_id=#{roleId}")
//    void delRoleDataPermission(String roleId);
//
//    /**
//     * 新增角色的数据权限信息
//     * @param role
//     */
//    @Update("UPDATE bi_role SET ROLE_DATAPERMISSION=#{roleDataPermissions} WHERE id =#{id}")
//    void updateRoleDataPermission(BiRole role);
}
