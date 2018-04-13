package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.vo.VoDataMap;

public interface IDataMapService {
	
	public VoResponse saveAll(List<MdmTableMap> list, List<VoDataMap> dataMaps);

	public void save(MdmTableMap tableMap);
	
	public VoResponse del(String id);
	
	public VoResponse update(MdmTableMap tableMap);
	
	public List<MdmTableMap> getById(String modelid, String bsid);
	
	public VoResponse syncToMdm(MdmModel model, MdmBs bs, List<MdmTableMap> list, String processId);
	
	public VoResponse send(MdmModel model, MdmBs bs, List<MdmTableMap> list, String processId);
	
	public VoResponse syncToMdm(String modelId, String processId);
	
	public VoResponse send(String modelId, String processId);
	
	public VoResponse syncToMdm(String modelId);
	
	public VoResponse send(String modelId);
	
	public void setMdmTableMap(TableDefinition table);
	
	public VoResponse getMdmTableMapById(MdmModel model, String bsId);
	
	public VoResponse getDataMapMdm(MdmModel model, String bsId, String order);
	
}
