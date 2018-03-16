package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.entity.ServiceInterfaceParam;

public interface IServiceInterfaceParamService {
	void add(ServiceInterfaceParam entity);
	
	void del(String id);
	
	void update(ServiceInterfaceParam entity);
	
	List<ServiceInterfaceParam> getAll();
}
