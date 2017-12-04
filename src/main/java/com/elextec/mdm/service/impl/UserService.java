package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.UserMapper;
import com.elextec.mdm.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return "123";
	}
	
	public boolean registerUser(User user){
		userMapper.insert(user);
		return true;
	}

	@Override
	public List<User> getAll() {
		List<User> list =  userMapper.getAll();
		return list;
	}

}
