package com.elextec.bi.service;

import com.elextec.bi.entity.BiRole;
import com.elextec.bi.common.entity.VoResponse;


import java.util.List;

public interface IBiRoleService {

	List<BiRole> getAllRoles();

	BiRole getRoleById(String id);

	VoResponse delRole(String id);

	VoResponse addRole(BiRole role);

	VoResponse updateRole(BiRole role);
	
	VoResponse updateRoleMenu(BiRole role);
	
//	VoResponse addRoleDataPermission(DataPermission dataPermission);
}
