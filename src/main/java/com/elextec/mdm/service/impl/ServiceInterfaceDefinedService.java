package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.ServiceInterfaceDefined;
import com.elextec.mdm.mapper.ServiceInterfaceDefinedMapper;
import com.elextec.mdm.service.IServiceInterfaceDefinedService;

@Service
public class ServiceInterfaceDefinedService implements IServiceInterfaceDefinedService {

	@Autowired
	private ServiceInterfaceDefinedMapper serviceInterfaceDefinedMapper;
	
	@Override
	public void add(ServiceInterfaceDefined entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ServiceInterfaceDefined entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ServiceInterfaceDefined> getAll() {
		List<ServiceInterfaceDefined> list = serviceInterfaceDefinedMapper.findAll();
		return list;
	}

	@Override
	public ServiceInterfaceDefined getById(String id) {
		ServiceInterfaceDefined serviceInterfaceDefined = serviceInterfaceDefinedMapper.findById(id);
		return serviceInterfaceDefined;
	}
	
	

}
