package com.elextec.bi.service;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.VoResult;
import com.elextec.bi.entity.BiUser;

import java.util.List;
import java.util.Map;

public interface IBiUserService {
	public VoResult add(BiUser user);
	
	public VoResponse signIn(String userName, String pwd);
	
	public List<BiUser> getAll();
	
	public Map<String, Object> getPage(BiUser user, int page, int pageSize);
	
	public VoResponse del(String userId);
	
	public VoResponse update(BiUser user);
	
	public VoResponse updateUserRole(BiUser user);
	
	public BiUser getById(String userId);
	
}
