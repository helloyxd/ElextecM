package com.elextec.mdm.entity;

import java.util.List;
import java.util.Map;

public class ColumnDefinition {

	private String name;
	private String columnComment;
	private Map<String, String> dataTypeMap;
	private List<String> constraints;
	
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
