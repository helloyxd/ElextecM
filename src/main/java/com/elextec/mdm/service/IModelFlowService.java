package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ModelFlow;

public interface IModelFlowService {
	VoResponse addOrUpdate(ModelFlow entity);
	
	VoResponse del(String id);
	
	VoResponse delByActivitiId(String id);
	
	VoResponse getByModelId(String id);
	
	ModelFlow getByModelAndType(String id, String type);
	
	ModelFlow getModelFlowByActivitiId(String id);
	 
	VoResponse getByActivitiId(String id);
	
	List<ModelFlow> getAll();
}
