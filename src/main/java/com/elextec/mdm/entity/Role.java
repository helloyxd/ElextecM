package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class Role extends BasicEntity{

	private String roleName;
	private String roleDesc;
	private List<Menu> menus;
	private List<DataPermission> dataPermissions;
	private int roleType;//角色类型，0菜单权限，1按钮权限
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	public List<DataPermission> getDataPermissions() {
	    return dataPermissions;
	}
	public void setDataPermissions(List<DataPermission> dataPermissions) {
	    this.dataPermissions = dataPermissions;
	}
	
}
