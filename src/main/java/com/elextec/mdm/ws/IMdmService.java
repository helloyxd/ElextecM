package com.elextec.mdm.ws;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.User;

@WebService
public interface IMdmService {
	
	@WebMethod
	public VoResult add(User user);
	
	@WebMethod
	public VoResponse signIn(String userName, String pwd);
	
	@WebMethod
	public List<User> getAll();
	
	public Map<String, Object> getPage(User user, int page, int pageSize);
	
	public VoResponse del(String userId);
	
	public VoResponse update(User user);
	
	public VoResponse updateUserRole(User user);
	
	public User getById(String userId);
	
}
