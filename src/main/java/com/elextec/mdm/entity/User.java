package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class User extends BasicEntity{

	private String userName;
	private String userPassword;
	private String fullName;
	private Integer departmentId;
	private Integer status;//用户状态,0正常，1锁定，2禁用，3删除
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
