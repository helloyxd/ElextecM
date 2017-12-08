package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;

public interface MenuMapper {

	@Select("SELECT * FROM menu ORDER BY sort_order")
	@Results(id="menuMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater"),
        @Result(property = "menus", column = "id",
            	many = @Many(select = "com.elextec.mdm.mapper.MenuMapper.findMenuByParentId"))
    })
	List<Menu> findAll();
	
	@Insert("INSERT INTO menu(menu_name,menu_url,parent_id,level,sort_order,status,remark,creater) "
			+ "VALUES(#{menuName},#{menuUrl},#{parentId},#{level},#{sortOrder},#{status},#{remark},#{creater})")
	@ResultMap("menuMap")
	void insert(Menu menu);
	
	@Delete("DELETE FROM menu WHERE id=#{menuId}")
	void delById(int menuId);
	
	@Update("UPDATE menu SET menu_name=#{menuName},menu_url=#{menuUrl},level=#{level},"
			+ "sort_order=#{sortOrder},remark=#{remark} WHERE id=#{id}")
	void update(Menu menu);
	
	@Select("SELECT * FROM Menu WHERE parent_id = #{parentId}")
	@ResultMap("menuMap")
	Menu findMenuByParentId(int parentId);
	
	/**
	 * 获取所有最高级菜单信息
	 * @return
	 */
	@Select("SELECT * FROM menu WHERE parent_id is null ORDER BY sort_order")
	@ResultMap("menuMap")
    List<Menu> findSuperMenus();
	
	/**
	 * 根据角色ID查询角色的菜单权限信息
	 * @param roleId
	 * @return
	 */
	@Select("SELECT * FROM menu WHERE id IN (SELECT menu_id FROM role_menu WHERE ROLE_ID = #{roleId})")
	@Results(id="menuMapOnly",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater")
    })
    List<Menu> findMenusByRoleId(Integer roleId);
	
}
