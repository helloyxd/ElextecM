package com.elextec.mdm.activity.impl;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.IModelFlowService;

public class DataPushService implements JavaDelegate {
	
	@Autowired
	IMdmModelService mdmModelService;
	
	@Autowired
	IDataMapService dataMapService;
	
	@Autowired
	IModelFlowService modelFlowService;
	
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		String proccessId = execution.getProcessDefinitionId();
		List<ModelFlow> modelFlowList = (List<ModelFlow>) modelFlowService.getByActivitiId(proccessId).getData();
		if(modelFlowList.size()>0) {
			dataMapService.send(modelFlowList.get(0).getModelId());
		}
	}

}
