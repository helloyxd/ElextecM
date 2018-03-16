package com.elextec.mdm.service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.QueryFieldDefined;

public interface IQueryFieldDefinedService {

	VoResponse add(QueryFieldDefined entity);
	
	VoResponse del(String id);
	
	VoResponse getById(String id);
}
