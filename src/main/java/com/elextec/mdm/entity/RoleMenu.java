package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class RoleMenu extends BasicEntity{

	private Integer roleId;
	private Integer menuId;
	private Integer roleType;
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
}
