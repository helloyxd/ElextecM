package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class DataPermissionDefined extends BasicEntity {

	private String tableId;
	private String permissionField;
	
	public String getPermissionField() {
		return permissionField;
	}
	public void setPermissionField(String permissionField) {
		this.permissionField = permissionField;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	
}
