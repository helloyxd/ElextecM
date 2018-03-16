package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.entity.ServiceParamTableDefined;

public interface IServiceParamTableDefinedService {

	void add(ServiceParamTableDefined entity);
	
	void del(String id);
	
	void update(ServiceParamTableDefined entity);
	
	List<ServiceParamTableDefined> getAll();
	
	ServiceParamTableDefined getById(String id);
}
