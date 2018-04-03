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
		VoResponse voRes = new VoResponse();
		queryFieldDefinedMapper.insert(entity);
		return voRes;
	}

	@Override
	public VoResponse del(String id) {
		VoResponse voRes = new VoResponse();
		queryFieldDefinedMapper.del(id);
		return voRes;
	}

	@Override
	public VoResponse getById(String id) {
		VoResponse voRes = new VoResponse();
		queryFieldDefinedMapper.findByTableId(id);
		voRes.setData(voRes);
		return voRes;
	}

}
