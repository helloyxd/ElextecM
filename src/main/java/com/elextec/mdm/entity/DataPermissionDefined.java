package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class DataPermissionDefined extends BasicEntity {

	private int tableId;
	private String permissionField;
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getPermissionField() {
		return permissionField;
	}
	public void setPermissionField(String permissionField) {
		this.permissionField = permissionField;
	}
	
	
}
