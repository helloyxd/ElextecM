package com.elextec.mdm.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.entity.User;

public class BaseService {

	@Autowired
	private HttpSession session;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public String getUserName(){
		Object obj = session.getAttribute("mdm_user");
		String username = "auto";
		if(obj != null){
			username = ((User)obj).getUserName();
		}
		return username;
	}
	
	public String getUserId(){
		Object obj = session.getAttribute("mdm_user");
		String userId = "798D5F70C6434815A1A3194C48695EC4";
		if(obj != null){
			userId = ((User)obj).getId();
		}
		return userId;
	}
}
