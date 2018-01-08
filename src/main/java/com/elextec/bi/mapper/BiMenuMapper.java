package com.elextec.bi.mapper;

import com.elextec.bi.entity.BiMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface BiMenuMapper {

	@Select("SELECT * FROM bi_menu ORDER BY sort_order")
	@Results(id="biMenuMapOnly",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "method", column = "method"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "menu_level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater")
    })
	List<BiMenu> findAll();
	
	@Select("SELECT * FROM bi_menu WHERE menu_level<=0 ORDER BY sort_order")
	@Results(id="biMenuMap",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "method", column = "method"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "menu_level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater"),
        @Result(property = "menus", column = "id",
            	many = @Many(select = "com.elextec.bi.mapper.BiMenuMapper.findMenuByParentId"))
    })
	List<BiMenu> findAllMenusTree();
	
	@Select("SELECT * FROM bi_menu WHERE menu_level=#{level} ORDER BY sort_order")
	@Results(id="biMenuMapAll",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "menuName",  column = "menu_name"),
        @Result(property = "menuUrl", column = "menu_url"),
        @Result(property = "method", column = "method"),
        @Result(property = "parentId", column = "parent_id"),
		@Result(property = "level", column = "menu_level"),
        @Result(property = "sortOrder", column = "sort_order"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "status", column = "status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "creater", column = "creater"),
        @Result(property = "menus", column = "id",
            	many = @Many(select = "com.elextec.bi.mapper.MenuMapper.findMenusByParentId"))
    })
	List<BiMenu> findAllByLevel(String level);
	
	@Select("SELECT * FROM bi_menu WHERE id=#{id} ")
	@ResultMap("biMenuMapAll")
	BiMenu findById(String id);
	
	@Select("SELECT * FROM bi_menu WHERE menu_name=#{menuName} ")
	@ResultMap("biMenuMapAll")
	List<BiMenu> findByName(String menuName);
	
	@Insert("INSERT INTO bi_menu(id,menu_name,menu_url,method,parent_id,menu_level,sort_order,status,remark,creater,create_time) "
			+ "VALUES(#{id},#{menuName},#{menuUrl},#{method},#{parentId,jdbcType=VARCHAR},#{level},#{sortOrder,jdbcType=INTEGER},#{status},#{remark,jdbcType=VARCHAR},#{creater},sysdate)")
	@SelectKey(before = true, keyProperty = "id",
		resultType = String.class, statementType = StatementType.STATEMENT,
		statement="SELECT sys_guid() FROM dual")
	void insert(BiMenu menu);
	
	@Delete("DELETE FROM bi_menu WHERE id=#{menuId}")
	void delById(String menuId);
	
	@Update("UPDATE bi_menu SET menu_name=#{menuName},menu_url=#{menuUrl},method=#{method},"
			+ "sort_order=#{sortOrder},parent_id=#{parentId,jdbcType=VARCHAR},remark=#{remark} WHERE id=#{id}")
	void update(BiMenu menu);
	
	/**
	 * 获取下级所有菜单
	 * @param parentId
	 * @return
	 */
	@Select("SELECT * FROM bi_menu WHERE parent_id=#{parentId}")
	@ResultMap("biMenuMapAll")
	BiMenu findMenusByParentId(String parentId);
	
	/**
	 * 获取下级菜单，不包括按钮功能菜单
	 * @param parentId
	 * @return
	 */
	@Select("SELECT * FROM bi_menu WHERE parent_id=#{parentId} AND menu_level<1000")
	@ResultMap("biMenuMap")
	BiMenu findMenuByParentId(String parentId);
	
	/**
	 * 获取所有最高级菜单信息
	 * @return
	 */
	@Select("SELECT * FROM bi_menu WHERE parent_id is null ORDER BY sort_order")
	@ResultMap("biMenuMap")
    List<BiMenu> findSuperMenus();
	
	/**
	 * 根据角色ID查询角色的菜单权限信息
	 * @param roleId
	 * @return
	 */
	@Select("SELECT * FROM bi_menu WHERE id IN (SELECT menu_id FROM bi_role_menu WHERE role_id = #{roleId})")
	@ResultMap("biMenuMapOnly")
    List<BiMenu> findMenusByRoleId(String roleId);
	
	
}
