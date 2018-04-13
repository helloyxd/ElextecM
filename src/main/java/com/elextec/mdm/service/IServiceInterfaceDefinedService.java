package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ServiceInterfaceDefined;


public interface IServiceInterfaceDefinedService {

	void add(ServiceInterfaceDefined entity);
	
	VoResponse addOrUpdate(ServiceInterfaceDefined entity);
	
	VoResponse del(String id);
	
	void update(ServiceInterfaceDefined entity);
	
	List<ServiceInterfaceDefined> getAll();
	
	ServiceInterfaceDefined getById(String id);
	
	public ServiceInterfaceDefined getQuery(String query);
	
	public Map<String, Object> getPage(ServiceInterfaceDefined entity, int page, int pageSize);
}
