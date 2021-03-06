package com.elextec.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import com.elextec.mdm.entity.Menu;

public interface MenuMapper {

	@Select("SELECT * FROM mdm_menu ORDER BY sort_order")
	@Results(id="menuMapOnly",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "method", column = "method"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "menu_level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "icon", column = "icon"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater")
    })
	List<Menu> findAll();
	
	@Select("SELECT * FROM mdm_menu WHERE menu_level<=0 AND parent_id IS NULL ORDER BY sort_order")
	@Results(id="menuMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "method", column = "method", jdbcType = JdbcType.VARCHAR),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "menu_level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "icon", column = "icon"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater"),
        @Result(property = "menus", column = "id",
            	many = @Many(select = "com.elextec.mdm.mapper.MenuMapper.findMenuByParentId"))
    })
	List<Menu> findAllMenusTree();
	
	@Select("SELECT * FROM mdm_menu WHERE menu_level=#{level} ORDER BY sort_order")
	@Results(id="menuMapAll",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "method", column = "method"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "menu_level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "icon", column = "icon"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater"),
        @Result(property = "menus", column = "id",
            	many = @Many(select = "com.elextec.mdm.mapper.MenuMapper.findMenusByParentId"))
    })
	List<Menu> findAllByLevel(String level);
	
	@Select("SELECT * FROM mdm_menu WHERE id=#{id} ")
	@ResultMap("menuMapAll")
	Menu findById(String id);
	
	@Select("SELECT * FROM mdm_menu WHERE id=#{id} ")
	@ResultMap("menuMapOnly")
	Menu findMenuById(String id);
	
	@Select("SELECT * FROM mdm_menu WHERE menu_name=#{menuName} ")
	@ResultMap("menuMapAll")
	List<Menu> findByName(String menuName);
	
	@Insert("INSERT INTO mdm_menu(id,menu_name,menu_url,method,parent_id,menu_level,sort_order,status,remark,icon,creater,create_time) "
			+ "VALUES(#{id},#{menuName},#{menuUrl,jdbcType=VARCHAR},#{method,jdbcType=VARCHAR},#{parentId,jdbcType=VARCHAR},#{level},"
			+ "#{sortOrder,jdbcType=INTEGER},#{status},#{remark,jdbcType=VARCHAR},#{icon,jdbcType=VARCHAR},#{creater,jdbcType=VARCHAR},sysdate)")
	@SelectKey(before = true, keyProperty = "id",
		resultType = String.class, statementType = StatementType.STATEMENT,
		statement="SELECT sys_guid() FROM dual")
	void insert(Menu menu);
	
	@Delete("DELETE FROM mdm_menu WHERE id=#{menuId}")
	void delById(String menuId);
	
	@Update("UPDATE mdm_menu SET menu_name=#{menuName},menu_url=#{menuUrl},method=#{method,jdbcType=VARCHAR},"
			+ "sort_order=#{sortOrder},parent_id=#{parentId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR},icon=#{icon,jdbcType=VARCHAR} WHERE id=#{id}")
	@ResultMap("menuMap")
	void update(Menu menu);
	
	/**
	 * 获取下级所有菜单
	 * @param parentId
	 * @return
	 */
	@Select("SELECT * FROM mdm_menu WHERE parent_id=#{parentId} ORDER BY sort_order")
	@ResultMap("menuMapAll")
	Menu findMenusByParentId(String parentId);
	
	/**
	 * 获取下级菜单，不包括按钮功能菜单
	 * @param parentId
	 * @return
	 */
	@Select("SELECT * FROM mdm_menu WHERE parent_id=#{parentId} AND menu_level<1000")
	@ResultMap("menuMap")
	Menu findMenuByParentId(String parentId);
	
	/**
	 * 获取所有最高级菜单信息
	 * @return
	 */
	@Select("SELECT * FROM mdm_menu WHERE parent_id is null ORDER BY sort_order")
	@ResultMap("menuMap")
    List<Menu> findSuperMenus();
	
	/**
	 * 根据角色ID查询角色的菜单权限信息
	 * @param roleId
	 * @return
	 */
	@Select("SELECT * FROM mdm_menu WHERE id IN (SELECT menu_id FROM mdm_role_menu WHERE role_id = #{roleId})")
	@ResultMap("menuMapOnly")
    List<Menu> findMenusByRoleId(String roleId);
	
	@Select("SELECT count(*) FROM mdm_menu")
	@ResultType(Integer.class)
	int queryCount();
	
}
