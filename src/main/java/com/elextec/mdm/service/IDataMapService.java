package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmDataMap;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.vo.VoDataMap;

public interface IDataMapService {
	
	public VoResponse saveAll(List<MdmTableMap> list, List<VoDataMap> dataMaps);

	public VoResponse save(VoDataMap dataMap);
	
	public VoResponse del(VoDataMap dataMap);
	
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
	
	
	Map<String, Object> getPage(String tableName, Map<String, String> queryParam, int page, int pageSize, String order);

	List<MdmDataMap> getDataMapById(String modelId, String bsId);

	Map<String, Object> getPage(String tableName, Map<String, String> queryParam, String modelId, String bsId, int page,
			int pageSize, String order, boolean isSelect, boolean isMain);
	
	
	public VoResponse saveAll(List<MdmDataMap> list);
	
	public VoResponse delAll(List<MdmDataMap> list);
}
