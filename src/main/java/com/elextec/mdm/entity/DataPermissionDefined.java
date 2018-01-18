package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class DataPermissionDefined extends BasicEntity {

	private String tableId;
	private TableDefinition tableDefinition;
	private String permissionField;
	private List<DataPermission> dataPermission;
	
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
	public List<DataPermission> getDataPermission() {
		return dataPermission;
	}
	public void setDataPermission(List<DataPermission> dataPermission) {
		this.dataPermission = dataPermission;
	}
	public TableDefinition getTableDefinition() {
		return tableDefinition;
	}
	public void setTableDefinition(TableDefinition tableDefinition) {
		this.tableDefinition = tableDefinition;
	}
	
	
}
