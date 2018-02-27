package com.elextec.mdm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataStructureMap;
import com.elextec.mdm.mapper.DataStructureMapper;
import com.elextec.mdm.mapper.MdmDataMapMapper;
import com.elextec.mdm.mapper.MdmTableMapMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IDataStructureMapService;

public class DataStructureMapService extends BaseService implements IDataStructureMapService{

	@Autowired
	private DataStructureMapper dataStructureMapper;
	
	@Autowired
	private MdmDataMapMapper dataMapMapper;
	
	@Autowired
	private MdmTableMapMapper tableMapMapper;
	
	@Override
	public VoResponse save(DataStructureMap dataStructureMap) {
		VoResponse voRes = new VoResponse();
		dataStructureMap.setCreater(getUserName());
		dataStructureMapper.insert(dataStructureMap);
		return voRes;
	}
	
	

}
