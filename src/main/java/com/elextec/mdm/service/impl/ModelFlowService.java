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
	public VoResponse add(ModelFlow entity) {
		VoResponse voRes = new VoResponse();
		modelFlowMapper.insert(entity);
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
	public VoResponse getByActivitiId(String id) {
		VoResponse voRes = new VoResponse();
		List<ModelFlow>  list = modelFlowMapper.findByActivitiId(id);
		voRes.setData(list);
		return voRes;
	}

}
