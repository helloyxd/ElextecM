package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Role;

public interface IRoleService {

	List<Role> getAllRoles();

	VoResponse delRole(String id);

	VoResponse addRole(Role role);

	VoResponse updateRole(Role role);
	
	VoResponse updateRoleMenu(Role role);
}
