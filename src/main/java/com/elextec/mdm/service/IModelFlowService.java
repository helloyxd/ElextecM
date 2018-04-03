package com.elextec.mdm.service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ModelFlow;

public interface IModelFlowService {
	VoResponse add(ModelFlow entity);
	
	VoResponse del(String id);
	
	VoResponse getByModelId(String id);
	
	VoResponse getByActivitiId(String id);
}
