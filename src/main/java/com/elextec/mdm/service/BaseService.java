package com.elextec.mdm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.contorller.UserController;
import com.elextec.mdm.entity.User;

public class BaseService {

	//@Autowired
	//private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;

	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private HttpSession getSession(HttpServletRequest request){
		String token = request.getHeader("token");
		HttpSession session = null;
		if(token != null) {
			session = UserController.sessionMap.get(token);
		}
		return session;
	}

	public String getUserName(){
		String username = "auto";
		HttpSession session = getSession(request);
		Object obj = session.getAttribute("mdm_user");
		if(obj != null){
			username = ((User)obj).getUserName();
		}
		return username;
	}
	
	public String getUserId(){
		HttpSession session = getSession(request);
		Object obj = session.getAttribute("mdm_user");
		String userId = null;
		//userId = "64365789BCA1ECF1E05013AC0688161E";
		if(obj != null){
			userId = ((User)obj).getId();
		}
		return userId;
	}

	
}
