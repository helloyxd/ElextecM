package com.elextec.mdm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleService roleService;
	
}
