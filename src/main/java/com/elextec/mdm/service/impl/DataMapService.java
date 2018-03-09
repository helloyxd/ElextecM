package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.mapper.MdmDataMapMapper;
import com.elextec.mdm.mapper.MdmTableMapMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.vo.VoDataMap;

@Service
public class DataMapService extends BaseService implements IDataMapService{

	@Autowired
	private MdmDataMapMapper dataMapMapper;
	
	@Autowired
	private MdmTableMapMapper tableMapMapper;
	
	
	
	@Override
	public void save(MdmTableMap tableMap) {
		tableMap.setCreater(getUserName());
		tableMapMapper.insert(tableMap);
	}

	@Override
	public VoResponse del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoResponse update(MdmTableMap tableMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MdmTableMap> getById(String modelid, String bsid) {
		return tableMapMapper.findByTableId(bsid, modelid);
	}

	@Override
	public VoResponse saveAll(List<MdmTableMap> list, List<VoDataMap> dataMaps) {
		VoResponse voRes = new VoResponse();
		String username = getUserName();
		for(VoDataMap dataMap : dataMaps){
			tableMapMapper.delByTableId(dataMap.getBsTableId(), dataMap.getMdmTableId());
		}
		for(MdmTableMap entity : list){
			entity.setCreater(username);
			tableMapMapper.insert(entity);
		}
		return voRes;
	}

}
