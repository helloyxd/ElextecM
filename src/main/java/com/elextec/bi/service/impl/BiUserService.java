package com.elextec.bi.service.impl;

import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.VoResult;
import com.elextec.bi.common.entity.constant.UserStatusEnum;
import com.elextec.bi.entity.BiUser;
import com.elextec.bi.mapper.BiUserMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiUserService;
import com.elextec.mdm.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Primary
@Service
public class BiUserService extends BiBaseService implements IBiUserService{

	@Autowired
	private BiUserMapper biUserMapper;
	
	@Override
	public VoResult add(BiUser user){
		VoResult vor = new VoResult();
		if(validata(user.getUserName())){
			vor.setMsg("用户名'" + user.getUserName() + "'已存在");
			vor.setResult(false);
		}else{
			user.setStatus(UserStatusEnum.UserStatusNormal);
			user.setCreater(getUserName());
			biUserMapper.insert(user);
			vor.setResult(true);
		}
		return vor;
	}
	
	@Override
	public VoResponse signIn(String userName, String pwd) {
		VoResponse voRes = new VoResponse();
		List<BiUser> list = biUserMapper.findUserByName(userName);
		if(list != null && list.size() > 0){
			for(BiUser user : list){
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
		List<BiUser> list = biUserMapper.findUserByName(userName);
		if(list != null && list.size() > 0){
			for(BiUser user : list){
				if(user.getUserPassword().equals(pwd)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean validata(String userName) {
		List<BiUser> list = biUserMapper.findUserByName(userName);
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<BiUser> getAll() {
		List<BiUser> list =  biUserMapper.findAll();
		return list;
	}

	@Override
	public Map<String, Object> getPage(BiUser user, int page, int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = biUserMapper.findCount(user);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(page);
		pageQuery.setPageRowSize(pageSize);
		pageQuery.setOrder("user_name");
		pageQuery.calcutePage(pageQuery);
		List<BiUser> list = biUserMapper.findUserByPage(user, pageQuery);
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	@Override
	public VoResponse del(String id){
		VoResponse voRes = new VoResponse();
		BiUser user = biUserMapper.findUserById(id);
		if(user == null){
			voRes.setFail(voRes);
			voRes.setMessage("User is not exist");
		}else{
			user.setStatus(UserStatusEnum.UserStatusDel);
			biUserMapper.update(user);
		}
		return voRes;
	}
	
	@Override
	public VoResponse update(BiUser user){
		VoResponse voRes = new VoResponse();
		BiUser existUser = biUserMapper.findUserById(user.getId());
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
		biUserMapper.update(user);
		return voRes;
	}
	
	@Override
	public VoResponse updateUserRole(BiUser user){
		VoResponse voRes = new VoResponse();
		biUserMapper.delUserRoles(user.getId());
		biUserMapper.addUserRoles(user);
		return voRes;
	}
	
	@Override
	public BiUser getById(String userId){
		BiUser user = biUserMapper.findUserById(userId);
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
