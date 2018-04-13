package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.entity.ServiceInterfaceDefined;


public interface IServiceInterfaceDefinedService {

	void add(ServiceInterfaceDefined entity);
	
	void del(String id);
	
	void update(ServiceInterfaceDefined entity);
	
	List<ServiceInterfaceDefined> getAll();
	
	ServiceInterfaceDefined getById(String id);
	
	public Map<String, Object> getPage(ServiceInterfaceDefined entity, int page, int pageSize);
}
