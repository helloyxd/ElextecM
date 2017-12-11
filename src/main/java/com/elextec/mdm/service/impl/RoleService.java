package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleService roleService;

	@Override
	public List<Role> getAllRoles() {
		List<Role> list = roleService.getAllRoles();
		return list;
	}

	@Override
	public VoResponse delRole(int id) {
		roleService.delRole(id);
		return null;
	}

	@Override
	public VoResponse addRole(Role role) {
		roleService.addRole(role);
		return null;
	}

	@Override
	public VoResponse updateRole(Role role) {
		roleService.updateRole(role);
		return null;
	}
	
}
