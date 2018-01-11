package com.elextec.mdm.entity;

import java.util.List;
import java.util.Map;

public class ColumnDefinition {

	private String name;//数据库字段名称
	private String columnComment;//字段中文名称
	private Map<String, String> dataTypeMap;//字段数据类型
	private List<String> constraints;//字段约束条件
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public Map<String, String> getDataTypeMap() {
		return dataTypeMap;
	}
	public void setDataTypeMap(Map<String, String> dataTypeMap) {
		this.dataTypeMap = dataTypeMap;
	}
	public List<String> getConstraints() {
		return constraints;
	}
	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}
	
}
