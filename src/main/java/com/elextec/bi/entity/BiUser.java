package com.elextec.bi.entity;

import com.elextec.mdm.common.entity.BasicEntity;
import com.elextec.mdm.entity.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BiUser extends BasicEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userPassword;
	private String fullName;
	private Department department;
	private List<BiRole> roles;
	private String sessionId;
	private List<BiMenu> menus;
	//用户对应的数据权限
	private Map<String,List<Map<String,Object>>> dataPermissions;

	public Map<String, List<Map<String, Object>>> getDataPermissions() {
		return dataPermissions;
	}

	public void setDataPermissions(Map<String, List<Map<String, Object>>> dataPermissions) {
		this.dataPermissions = dataPermissions;
	}

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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<BiRole> getRoles() {
		return roles;
	}
	public void setRoles(List<BiRole> roles) {
		this.roles = roles;
	}
	@JsonIgnore
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public List<BiMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<BiMenu> menus) {
		this.menus = menus;
	}
	
}
