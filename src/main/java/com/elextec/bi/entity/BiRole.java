package com.elextec.bi.entity;

import com.elextec.bi.common.entity.BasicEntity;
import com.elextec.mdm.entity.DataPermission;

import java.util.List;

public class BiRole extends BasicEntity{

	private String roleName;
	private String roleDesc;
	private List<BiMenu> menus;
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
	public List<BiMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<BiMenu> menus) {
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
