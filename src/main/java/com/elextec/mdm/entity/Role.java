package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class Role extends BasicEntity{

	private String roleName;
	private String roleDesc;
	
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
	
}
