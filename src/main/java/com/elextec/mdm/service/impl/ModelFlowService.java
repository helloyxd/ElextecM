package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.mapper.ModelFlowMapper;
import com.elextec.mdm.service.IModelFlowService;

@Service
public class ModelFlowService implements IModelFlowService {

	@Autowired
	private ModelFlowMapper modelFlowMapper;
	
	@Override
	public VoResponse addOrUpdate(ModelFlow entity) {
		VoResponse voRes = new VoResponse();
		if(entity.getId() == null){
			modelFlowMapper.insert(entity);
		}else{
			modelFlowMapper.update(entity);
		}
		return voRes;
	}

	@Override
	public VoResponse del(String id) {
		VoResponse voRes = new VoResponse();
		modelFlowMapper.del(id);
		return voRes;
	}

	@Override
	public VoResponse getByModelId(String id) {
		VoResponse voRes = new VoResponse();
		List<ModelFlow>  list = modelFlowMapper.findByModelId(id);
		voRes.setData(list);
		return voRes;
	}
	
	@Override
	public ModelFlow getModelFlowByActivitiId(String id) {
		List<ModelFlow>  list = modelFlowMapper.findByActivitiId(id);
		if(list == null || list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public VoResponse getByActivitiId(String id) {
		VoResponse voRes = new VoResponse();
		List<ModelFlow>  list = modelFlowMapper.findByActivitiId(id);
		voRes.setData(list);
		return voRes;
	}

	@Override
	public VoResponse getAll() {
		VoResponse voRes = new VoResponse();
		List<ModelFlow> list = modelFlowMapper.findAll();
		voRes.setData(list);
		return voRes;
	}

}
