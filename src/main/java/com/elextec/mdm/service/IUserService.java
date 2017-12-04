package com.elextec.mdm.service;

import com.elextec.mdm.entity.User;

public interface IUserService {
	public String getUserName();
	public boolean registerUser(User user);
}
