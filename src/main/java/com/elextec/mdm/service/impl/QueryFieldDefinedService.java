package com.elextec.mdm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.QueryFieldDefined;
import com.elextec.mdm.mapper.QueryFieldDefinedMapper;
import com.elextec.mdm.service.IQueryFieldDefinedService;

@Service
public class QueryFieldDefinedService implements IQueryFieldDefinedService {
	
	@Autowired
	private QueryFieldDefinedMapper queryFieldDefinedMapper;

	@Override
	public VoResponse add(QueryFieldDefined entity) {
		return null;
	}

	@Override
	public VoResponse del(String id) {
		return null;
	}

	@Override
	public VoResponse getById(String id) {
		return null;
	}

}
