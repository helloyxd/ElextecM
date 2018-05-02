package com.elextec.mdm.webservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.service.impl.TableDDLService;
import com.elextec.mdm.webservice.IMdmService;

//@WebService(serviceName="MdmService", endpointInterface = "com.elextec.mdm.webservice.IMdmService")
public class MdmService implements IMdmService {

	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	@Autowired
	private TableDDLService tableDDLService;
	
	public List<String> getModels() {
		List<String> list = new ArrayList<String>();
		List<MdmModel> models = mdmModelMapper.findAll();
		for(MdmModel model : models) {
			Map<String,String> map = new HashMap<String,String>();
			map.put(model.getId(), model.getMdmModel());
			list.add(model.getMdmModel());
		}
		return list;
	}

	
    public String excute(String param)
    {
        System.out.println(param);
        return "hello world!";
    }
    
	public VoResponse postDefinedData(String model, Map<String,String> map) {
		List<MdmModel> models = mdmModelMapper.findByName(model);
		TableDefinition table = null;
		table = models.get(0).getTableDefinitions().get(0);
		VoResponse voRes = tableDDLService.postDefinedData(table, map);
		return voRes;
	}

}
