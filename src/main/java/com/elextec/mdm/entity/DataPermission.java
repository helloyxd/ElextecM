package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class DataPermission extends BasicEntity{

	private int definedId;
	private int roleId;
	private String permissionValue;
	private List<DataPermissionDefined> dataPermissionDefineds;
	
	public int getDefinedId() {
		return definedId;
	}
	public void setDefinedId(int definedId) {
		this.definedId = definedId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
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
	
	
}
