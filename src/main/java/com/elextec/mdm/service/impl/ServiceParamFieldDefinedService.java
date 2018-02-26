package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.ServiceParamFieldDefined;
import com.elextec.mdm.mapper.ServiceParamFieldDefinedMapper;
import com.elextec.mdm.service.IServiceParamFieldDefinedService;

@Service
public class ServiceParamFieldDefinedService implements IServiceParamFieldDefinedService {

	@Autowired
	private ServiceParamFieldDefinedMapper serviceParamFieldDefinedMapper;
	
	@Override
	public void add(ServiceParamFieldDefined entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ServiceParamFieldDefined entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ServiceParamFieldDefined> getAll() {
		List<ServiceParamFieldDefined> list = serviceParamFieldDefinedMapper.findAll();
		return list;
	}

}
