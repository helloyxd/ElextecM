package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class TableDefinition extends BasicEntity{
	private Long id;
	private String tableName;
	private String tableDesc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	
}
