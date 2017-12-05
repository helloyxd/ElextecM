package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class TableDefinition extends BasicEntity{
	
	private String tableName;
	private String tableLable;
	private String modelId;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableLable() {
		return tableLable;
	}
	public void setTableLable(String tableLable) {
		this.tableLable = tableLable;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
}
