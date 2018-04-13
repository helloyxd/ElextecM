package com.elextec.mdm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.ServiceInterfaceDefined;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.ServiceInterfaceDefinedMapper;
import com.elextec.mdm.service.IServiceInterfaceDefinedService;

@Service
public class ServiceInterfaceDefinedService implements IServiceInterfaceDefinedService {

	@Autowired
	private ServiceInterfaceDefinedMapper serviceInterfaceDefinedMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	@Override
	public void add(ServiceInterfaceDefined entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public VoResponse del(String id) {
		VoResponse voRes = new VoResponse();
		ServiceInterfaceDefined entity = serviceInterfaceDefinedMapper.findById(id);
		if(entity == null){
			voRes.setNull(voRes);
			voRes.setMessage("id参数错误");
		}else if(entity.getSiParams().size() > 0){
			voRes.setFail(voRes);
			voRes.setMessage("接口已经生成参数，删除失败");
		}else{
			serviceInterfaceDefinedMapper.del(id);
		}
		return voRes;
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

	@Override
	public VoResponse addOrUpdate(ServiceInterfaceDefined entity) {
		VoResponse voRes = new VoResponse();
		if(entity.getModelId() != null && !entity.getModelId().equals("")){
			MdmModel model = mdmModelMapper.findByIdOnly(entity.getModelId());
			if(model == null){
				voRes.setNull(voRes);
				voRes.setMessage("modelId参数错误");
				return voRes;
			}
			if(entity.isDataSource() &&  serviceInterfaceDefinedMapper.findByModelId(entity.getModelId(), 1).size() > 1){
				voRes.setFail(voRes);
				voRes.setMessage("mdm模块" + model.getMdmModel() + "已经存在主数据源");
				return voRes;
			}
		}
		if(entity.getId() == null || entity.getId().equals("")){
			
			serviceInterfaceDefinedMapper.insert(entity);
			
		}else{
			ServiceInterfaceDefined oldEntity = serviceInterfaceDefinedMapper.findById(entity.getId());
			if(oldEntity == null){
				voRes.setNull(voRes);
				voRes.setMessage("id参数错误");
				return voRes;
			}
			serviceInterfaceDefinedMapper.update(entity);
		}
		return voRes;
	}
	
	

}
