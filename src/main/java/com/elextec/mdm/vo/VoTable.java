package com.elextec.mdm.vo;

import com.elextec.mdm.entity.TableDefinition;

public class VoTable {

	private String tableId;
	private TableDefinition tableDefinition;
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public TableDefinition getTableDefinition() {
		return tableDefinition;
	}
	public void setTableDefinition(TableDefinition tableDefinition) {
		this.tableDefinition = tableDefinition;
	}
}
