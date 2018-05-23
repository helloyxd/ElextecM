package com.elextec.bi.service;

import com.elextec.bi.entity.BiUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BiBaseService {

	@Autowired
	private HttpSession session;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public String getUserName(){
		Object obj = session.getAttribute("bi_user");
		String username = "auto";
		if(obj != null){
			username = ((BiUser)obj).getUserName();
		}
		return username;
	}
	
	public String getUserId(){
		Object obj = session.getAttribute("bi_user");
		String userId = "798D5F70C6434815A1A3194C48695EC4";
		if(obj != null){
			userId = ((BiUser)obj).getId();
		}
		return userId;
	}
}
