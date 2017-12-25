package com.elextec.mdm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.common.entity.constant.UserStatusEnum;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.UserMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IUserService;

@Service
public class UserService extends BaseService implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public VoResult add(User user){
		VoResult vor = new VoResult();
		if(validata(user.getUserName())){
			vor.setMsg("用户名'" + user.getUserName() + "'已存在");
			vor.setResult(false);
		}else{
			user.setCreater(getUserName());
			userMapper.insert(user);
			vor.setResult(true);
		}
		return vor;
	}
	
	@Override
	public VoResponse signIn(String userName, String pwd) {
		VoResponse voRes = new VoResponse();
		List<User> list = userMapper.findUserByName(userName);
		if(list != null && list.size() > 0){
			for(User user : list){
				if(user.getUserPassword().equals(pwd)){
					voRes.setData(user);
					return voRes;
				}
			}
			voRes.setFail(voRes);
			voRes.setMessage("password error");
			return voRes;
		}else{
			voRes.setFail(voRes);
			voRes.setMessage("userName does not exist");
			return voRes;
		}
	}
	
	public boolean validata(String userName, String pwd) {
		List<User> list = userMapper.findUserByName(userName);
		if(list != null && list.size() > 0){
			for(User user : list){
				if(user.getUserPassword().equals(pwd)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean validata(String userName) {
		List<User> list = userMapper.findUserByName(userName);
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAll() {
		List<User> list =  userMapper.findAll();
		return list;
	}

	@Override
	public Map<String, Object> getPage(User user, int page, int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = userMapper.findCount(user);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(page);
		pageQuery.setPageRowSize(pageSize);
		pageQuery.setOrder("user_name");
		pageQuery.calcutePage(pageQuery);
		List<User> list = userMapper.findUserByPage(user, pageQuery);
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	@Override
	public VoResponse del(String id){
		VoResponse voRes = new VoResponse();
		User user = userMapper.findUserById(id);
		if(user == null){
			voRes.setFail(voRes);
			voRes.setMessage("User is not exist");
		}else{
			user.setStatus(UserStatusEnum.UserStatusDel);
			userMapper.update(user);
		}
		return voRes;
	}
	
	@Override
	public VoResponse update(User user){
		VoResponse voRes = new VoResponse();
		User existUser = userMapper.findUserById(user.getId());
		if(existUser == null){
			voRes.setFail(voRes);
			voRes.setMessage("User is not exist");
			return voRes;
		}
		if(!existUser.getUserName().equals(user.getUserName())){
			if(validata(user.getUserName())){
				voRes.setFail(voRes);
				voRes.setMessage("userName alreadly exist");
				return voRes;
			}
		}
		userMapper.update(user);
		return voRes;
	}
	
	@Override
	public VoResponse updateUserRole(User user){
		VoResponse voRes = new VoResponse();
		userMapper.delUserRoles(user.getId());
		userMapper.addUserRoles(user);
		return voRes;
	}
	
	@Override
	public User getById(String userId){
		User user = userMapper.findUserById(userId);
		return user;
	}
	
	public List<Menu> getUserMenuById(String userId){
		
		
		return null;
	}
	

	public VoResponse method(String id){
		VoResponse voRes = new VoResponse();
		
		return voRes;
	}


}
