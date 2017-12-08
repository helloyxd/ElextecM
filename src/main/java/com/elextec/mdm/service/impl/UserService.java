package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.RoleMapper;
import com.elextec.mdm.mapper.UserMapper;
import com.elextec.mdm.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	
	@Override
	public String getUserName() {
		return "123";
	}
	
	public VoResult registerUser(User user){
		VoResult vor = new VoResult();
		List<User> list = userMapper.findUserByName(user.getUserName());
		if(list != null && list.size() > 0){
			vor.setMsg("用户名'" + user.getUserName() + "'已存在");
			vor.setResult(false);
		}else{
			userMapper.insert(user);
			vor.setResult(true);
		}
		return vor;
	}

	@Override
	public List<User> getAll() {
		List<User> list =  userMapper.findAll();
		return list;
	}
	
}
