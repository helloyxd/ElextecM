package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class TableDefinition extends BasicEntity{
	
	private String tableName;
	private String tableLabel;//table标签名
	private String modelId;//模块ID
	private Boolean isMenu;
	private List<ColumnDefinition> columnDefinitions;//字段属性
	private MdmModel model;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<ColumnDefinition> getColumnDefinitions() {
		return columnDefinitions;
	}
	public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
		this.columnDefinitions = columnDefinitions;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getTableLabel() {
		return tableLabel;
	}
	public void setTableLabel(String tableLabel) {
		this.tableLabel = tableLabel;
	}
	public Boolean getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}
	public MdmModel getModel() {
		return model;
	}
	public void setModel(MdmModel model) {
		this.model = model;
	}
	
}
