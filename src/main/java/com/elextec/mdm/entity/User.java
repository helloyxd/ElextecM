package com.elextec.mdm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.elextec.mdm.common.entity.BasicEntity;

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
	
	private UserAddtion userAddtion;
	
	@XmlElementWrapper(name = "roles")  
	@XmlElement(name = "role")  
	private List<Role> roles;
	
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
	
	
}
