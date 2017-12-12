package com.elextec.mdm.entity;

import java.util.Map;

public class ColumnDefinition {

	private String name;
	private Map<Integer, String> dataTypeMap;
	private String constraint;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Integer, String> getDataTypeMap() {
		return dataTypeMap;
	}
	public void setDataTypeMap(Map<Integer, String> dataTypeMap) {
		this.dataTypeMap = dataTypeMap;
	}
	public String getConstraint() {
		return constraint;
	}
	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}
	
	
}
