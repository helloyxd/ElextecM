package com.elextec.mdm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.elextec.mdm.common.entity.BasicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD) 
public class User extends BasicEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userPassword;
	private String fullName;
	private Department department;
	private String dpartmentId;
	private UserAddtion userAddtion;
	@XmlElementWrapper(name = "roles")  
	@XmlElement(name = "role")  
	private List<Role> roles;
	private String sessionId;
	private List<Menu> menus;
	
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
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public UserAddtion getUserAddtion() {
		return userAddtion;
	}
	public void setUserAddtion(UserAddtion userAddtion) {
		this.userAddtion = userAddtion;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public String getDpartmentId() {
		return dpartmentId;
	}
	public void setDpartmentId(String dpartmentId) {
		this.dpartmentId = dpartmentId;
	}
	
}
