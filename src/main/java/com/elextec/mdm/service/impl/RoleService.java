package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.mapper.RoleMapper;
import com.elextec.mdm.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getAllRoles() {
		List<Role> list = roleMapper.findAll();
		return list;
	}

	@Override
	public VoResponse delRole(String id) {
		VoResponse voRes = new VoResponse();
		roleMapper.delete(id);
		return voRes;
	}

	@Override
	public VoResponse addRole(Role role) {
		VoResponse voRes = new VoResponse();
		List<Role> list = roleMapper.findRoleByName(role.getRoleName());
		if(list != null && list.size() > 0 ){
			voRes.setFail(voRes);
			voRes.setMessage("roleName is exist");
			return voRes;
		}
		roleMapper.insert(role);
		return voRes;
	}

	@Override
	public VoResponse updateRole(Role role) {
		VoResponse voRes = new VoResponse();
		roleMapper.update(role);
		roleMapper.delRoleMenus(role.getId());
		roleMapper.addRoleMenus(role);
		return voRes;
	}

	@Override
	public VoResponse updateRoleMenu(Role role) {
		VoResponse voRes = new VoResponse();
		roleMapper.delRoleMenus(role.getId());
		roleMapper.addRoleMenus(role);
		return voRes;
	}
	
	
	
}
