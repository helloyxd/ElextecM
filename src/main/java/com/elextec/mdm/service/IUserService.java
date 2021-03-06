package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.User;

public interface IUserService {
	public VoResult add(User user);
	
	public VoResponse signIn(String userName, String pwd);
	
	public List<User> getAll();
	
	public Map<String, Object> getPage(User user, int page, int pageSize);
	
	public VoResponse del(String userId);
	
	public VoResponse update(User user);
	
	public VoResponse updateUserRole(List<User> users);
	
	public User getById(String userId);
	
	public List<Menu> getCurrentUserMenus();
	
	public List<Menu> getUserMenuById(String userId);
	
}
