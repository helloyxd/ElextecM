package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.entity.ServiceInterfaceDefined;


public interface IServiceInterfaceDefinedService {

	void add(ServiceInterfaceDefined entity);
	
	void del(String id);
	
	void update(ServiceInterfaceDefined entity);
	
	List<ServiceInterfaceDefined> getAll();
	
	ServiceInterfaceDefined getById(String id);
}
