package com.elextec.mdm.entity;

public class UserEntity {
	private Long id;
	private String userName;
	private String passWord;
	private Integer userSex;
	private String nickName;
	public UserEntity(String userName,String password,Integer sex) {
		this.userName = userName;
		this.passWord = password;
		this.userSex = sex;
	}
	
	public UserEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	
	
}
