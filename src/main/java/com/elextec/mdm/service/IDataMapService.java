package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.vo.VoDataMap;

public interface IDataMapService {
	
	public VoResponse saveAll(List<MdmTableMap> list, List<VoDataMap> dataMaps);

	public void save(MdmTableMap tableMap);
	
	public VoResponse del(String id);
	
	public VoResponse update(MdmTableMap tableMap);
	
	public List<MdmTableMap> getById(String modelid, String bsid);
	
	
}
