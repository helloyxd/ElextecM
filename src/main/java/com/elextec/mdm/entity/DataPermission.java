package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class DataPermission extends BasicEntity{

	private String definedId;
	private String roleId;
	private String permissionValue;
	private List<DataPermissionDefined> dataPermissionDefineds;
	
	public String getPermissionValue() {
		return permissionValue;
	}
	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}
	public List<DataPermissionDefined> getDataPermissionDefineds() {
		return dataPermissionDefineds;
	}
	public void setDataPermissionDefineds(List<DataPermissionDefined> dataPermissionDefineds) {
		this.dataPermissionDefineds = dataPermissionDefineds;
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
	
	
}
