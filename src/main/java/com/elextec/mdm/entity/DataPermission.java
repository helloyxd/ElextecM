package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class DataPermission extends BasicEntity{

	private String definedId;
	private String roleId;
	private String permissionValue;
	private DataPermissionDefined dataPermissionDefined;
	
	public String getPermissionValue() {
		return permissionValue;
	}
	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDefinedId() {
		return definedId;
	}
	public void setDefinedId(String definedId) {
		this.definedId = definedId;
	}
	public DataPermissionDefined getDataPermissionDefined() {
		return dataPermissionDefined;
	}
	public void setDataPermissionDefined(DataPermissionDefined dataPermissionDefined) {
		this.dataPermissionDefined = dataPermissionDefined;
	}
	
	
}
