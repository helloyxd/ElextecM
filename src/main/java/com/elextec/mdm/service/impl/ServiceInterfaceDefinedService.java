package com.elextec.mdm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.PageQuery;
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

	@Override
	public Map<String, Object> getPage(ServiceInterfaceDefined entity, int page, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("model_id", entity.getModelId());
		queryParam.put("bs_id", entity.getBsId());
		PageQuery pageQuery = new PageQuery();
		pageQuery.setTableName("mdm_serviceInterface_defined");
		int count = serviceInterfaceDefinedMapper.findCount(queryParam, pageQuery.getTableName());
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(page);
		pageQuery.setPageRowSize(pageSize);
		pageQuery.setOrder("create_time");
		pageQuery.calcutePage(pageQuery);
		List<ServiceInterfaceDefined> list = serviceInterfaceDefinedMapper.findByPage(queryParam, pageQuery);
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	

}
