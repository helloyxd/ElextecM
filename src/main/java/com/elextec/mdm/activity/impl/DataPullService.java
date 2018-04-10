package com.elextec.mdm.activity.impl;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.service.IActivitiService;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.IModelFlowService;

@Service
public class DataPullService implements JavaDelegate {
	
	@Autowired
	IMdmModelService mdmModelService;
	
	@Autowired
	IDataMapService dataMapService;
	
	@Autowired
	IModelFlowService modelFlowService;
	
	@Autowired
	private TaskService taskService;
	
	
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		//String proccessId = ((ExecutionEntityImpl)execution).getProcessDefinitionKey();
		
		String proccessInstanceId = ((ExecutionEntityImpl)execution).getProcessInstanceId();
		System.out.println(((ExecutionEntityImpl)execution).getActivityName());
		System.out.println(proccessInstanceId);
		List<ModelFlow> modelFlowList = (List<ModelFlow>) modelFlowService.getByActivitiId(proccessInstanceId).getData();
		if(modelFlowList.size()>0) {
			dataMapService.syncToMdm(modelFlowList.get(0).getModelId());
		}

	}
	
	
	
	
	

}
