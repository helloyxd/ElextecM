package com.elextec.mdm.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.webservice.ISampleService;

@WebService(endpointInterface = "com.elextec.mdm.webservice.ISampleService")
/*serviceName = "MpcService"*/
//targetNamespace="http://webservice.mdm.elextec.com/")
public class SampleService implements ISampleService {
		public User createMpc(User user) {  
	        Role role = new Role();
	        role.setRoleName("admin");
	        List<Role> roles = new ArrayList<Role>();
	        roles.add(role);
	        role = new Role();
	        role.setRoleName("admin1");
	        roles.add(role);
	        user.setRoles(roles);
			return user;  
	    }  
	
}
