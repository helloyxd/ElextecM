package com.elextec.mdm.service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataStructureMap;
import com.elextec.mdm.entity.MdmTableMap;

public interface IDataStructureMapService {

	public VoResponse save(DataStructureMap dataStructureMap);
	
	public void save(MdmTableMap tableMap);
	
	public void del(String id);
	
	public void update(MdmTableMap tableMap);
	
	public MdmTableMap getById(String id);
	
	
}
