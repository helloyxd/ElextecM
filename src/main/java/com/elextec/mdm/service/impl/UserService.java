package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.common.entity.constant.UserStatusEnum;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.MenuMapper;
import com.elextec.mdm.mapper.UserMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IUserService;

@Service
public class UserService extends BaseService implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public VoResult add(User user){
		VoResult vor = new VoResult();
		if(validata(user.getUserName())){
			vor.setMsg("用户名'" + user.getUserName() + "'已存在");
			vor.setResult(false);
		}else{
			user.setCreater(getUserName());
			userMapper.insert(user);
			if(user.getRoles() != null){
				updateUserRoles(user);
			}
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
	
	@Transactional
	public void updateUserRoles(User user){
		userMapper.delUserRoles(user.getId());
		userMapper.addUserRoles(user);
	}
	
	@Override
	public VoResponse updateUserRole(List<User> users){
		VoResponse voRes = new VoResponse();
		for(User user : users){
			updateUserRoles(user);
		}
		return voRes;
	}
	
	@Override
	public User getById(String userId){
		User user = userMapper.findUserById(userId);
		return user;
	}
	
	@Override
	public List<Menu> getCurrentUserMenus(){
		List<Menu> list =  getUserMenuById(getUserId());
		setMenu(list, null);
		return list;
	}
	
	private List<Menu> setMenu(List<Menu> list, Menu lastMenu){
		Iterator<Menu> it = list.iterator();
		List<Menu> listnull = new ArrayList<>();
		while (it.hasNext()) {
			Menu menu = it.next();
			menu.setLeaf(true);
			if(menu.getMenus().size() > 0 && menu.getLevel() < 1000){
				menu.setLeaf(false);
				setMenu(menu.getMenus(), menu);
			}else if(menu.getLevel() == 1000){
				lastMenu.setAuthmenus(list);
				lastMenu.setMenus(listnull);
				lastMenu.setLeaf(true);
			}
		}
		return list;
	}
	
	
	public List<Menu> getUserMenuById(String userId){
		User user = getById(userId);
		List<Role> roles = user.getRoles();
		List<Menu> myMenus = new ArrayList<Menu>();
		for(Role role : roles){
			if(role.getRoleName().equals("admin")){//管理员获取所有菜单
				myMenus = menuMapper.findAllByLevel("1");
				return myMenus;
			}
			List<Menu> menus = role.getMenus();
			myMenus = transMenus(myMenus, menus);
		}
		return myMenus;
	}
	
	private List<Menu> transMenus(List<Menu> myMenus, List<Menu> menus){
		for(Menu menu : menus){
			boolean flag = false;
			Menu e = null;
			Iterator<Menu> it = myMenus.iterator();
			while(it.hasNext()){
				Menu mymenu = it.next();
				if(mymenu.getId().equals(menu.getId())){
					flag = true;
					e = mymenu;
				}
			}
			if(!flag){
				menu.setMenus(null);
				e = menu;
				flag = false;
			}
			if(menu.getMenus() != null && menu.getMenus().size() > 0){
				List<Menu> subMenus = new ArrayList<Menu>();
				subMenus = transMenus(subMenus, menu.getMenus());
				e.setMenus(subMenus);
			}
			myMenus.add(e);
		}
		return myMenus;
	}
	
	public VoResponse method(String id){
		VoResponse voRes = new VoResponse();
		
		return voRes;
	}


}
