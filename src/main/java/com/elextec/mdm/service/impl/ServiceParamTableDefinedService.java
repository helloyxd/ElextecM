package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.ServiceParamTableDefined;
import com.elextec.mdm.mapper.ServiceParamTableDefinedMapper;
import com.elextec.mdm.service.IServiceParamTableDefinedService;

@Service
public class ServiceParamTableDefinedService implements IServiceParamTableDefinedService {

	@Autowired
	private ServiceParamTableDefinedMapper serviceParamTableDefinedMapper;
	
	@Override
	public void add(ServiceParamTableDefined entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ServiceParamTableDefined entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ServiceParamTableDefined> getAll() {
		List<ServiceParamTableDefined> list = serviceParamTableDefinedMapper.findAll();
		return list;
	}

	@Override
	public ServiceParamTableDefined getById(String id) {
		return serviceParamTableDefinedMapper.findById(id);
	}

}
