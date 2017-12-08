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

	@Select("SELECT * FROM role WHERE role_name = #{roleName}")
	@Results(id="roleMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "roleName",  column = "role_name"),
        @Result(property = "roleDesc", column = "role_desc"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
        @Result(property = "creater", column = "creater"),
        @Result(property = "menus", column = "id",
    	many = @Many(select = "com.elextec.mdm.mapper.MenuMapper.findMenusByRoleId")
    		)
    })
    List<Role> findRoleByName(String userName);
	
	@Select("SELECT * FROM role")
	@ResultMap("roleMap")
    List<Role> findAll();
	
	@Select("SELECT * FROM role WHERE id = #{roleId}")
	@ResultMap("roleMap")
	Role findRoleById(int roleId);

    @Insert("INSERT INTO role(role_name,role_desc,status,creater)"
    		+ " VALUES(#{roleName}, #{roleDesc}, #{status}, #{creater})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Role role);

    @Update("UPDATE role SET role_name=#{roleName},user_desc=#{roleDesc} WHERE id =#{id}")
    void update(Role role);

    @Delete("DELETE FROM role WHERE id =#{id}")
    void delete(Long id);
    
    @Update("${sql}")
    void createTable(@Param("sql") String sql);
    
    @Select("SELECT * FROM role WHERE id IN (SELECT role_id FROM user_role WHERE USER_ID = #{userId})")
    @ResultMap("roleMap")
    List<Role> findRolesByUserId(Integer userId);
    
    /**
     * 新增角色的菜单权限信息
     * @param user
     */
    @InsertProvider(type = MapperProvider.class,method = "addRoleMenus")
	void addRoleMenus(@Param("role")Role role);
    
    
}
