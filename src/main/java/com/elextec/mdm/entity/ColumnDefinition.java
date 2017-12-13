package com.elextec.mdm.entity;

import java.util.List;
import java.util.Map;

public class ColumnDefinition {

	private String name;
	private Map<Integer, String> dataTypeMap;
	private List<Integer> constraints;
	
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
	public List<Integer> getConstraints() {
		return constraints;
	}
	public void setConstraints(List<Integer> constraints) {
		this.constraints = constraints;
	}
	
}
