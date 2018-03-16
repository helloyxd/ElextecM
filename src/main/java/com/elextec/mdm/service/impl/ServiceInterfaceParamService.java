package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.ServiceInterfaceParam;
import com.elextec.mdm.mapper.ServiceInterfaceParamMapper;
import com.elextec.mdm.service.IServiceInterfaceParamService;

@Service
public class ServiceInterfaceParamService implements IServiceInterfaceParamService {

	@Autowired
	private ServiceInterfaceParamMapper serviceInterfaceParamMapper;
	
	@Override
	public void add(ServiceInterfaceParam entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ServiceInterfaceParam entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ServiceInterfaceParam> getAll() {
		List<ServiceInterfaceParam> list = serviceInterfaceParamMapper.findAll();
		return list;
	}

}
