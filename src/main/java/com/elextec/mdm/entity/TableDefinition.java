package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class TableDefinition extends BasicEntity{
	
	private String tableName;
	private String tableLable;//table标签名
	private int modelId;//模块ID
	private List<ColumnDefinition> columnDefinitions;//字段属性
	
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
	public List<ColumnDefinition> getColumnDefinitions() {
		return columnDefinitions;
	}
	public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	
}
