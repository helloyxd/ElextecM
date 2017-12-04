package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class UserRole extends BasicEntity{

	private Integer roleId;
	private Integer userId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
