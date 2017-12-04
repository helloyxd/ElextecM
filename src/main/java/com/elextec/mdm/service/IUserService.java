package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.entity.User;

public interface IUserService {
	public String getUserName();
	public boolean registerUser(User user);
	public List<User> getAll();
}
