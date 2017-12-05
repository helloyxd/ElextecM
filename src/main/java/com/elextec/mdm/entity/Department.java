package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class Department extends BasicEntity{

	private String departCode;
	private String departName;
	private String parentId;
	
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
