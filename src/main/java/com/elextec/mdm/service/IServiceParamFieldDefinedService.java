package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.entity.ServiceParamFieldDefined;

public interface IServiceParamFieldDefinedService {

	void add(ServiceParamFieldDefined entity);
	
	void del(String id);
	
	void update(ServiceParamFieldDefined entity);
	
	List<ServiceParamFieldDefined> getAll();
}
