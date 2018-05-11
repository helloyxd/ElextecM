package com.elextec.mdm.activity.impl;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.mapper.ModelFlowMapper;
import com.elextec.mdm.service.IModelFlowService;
import com.elextec.mdm.service.ITaskDataService;

@Service
public class TestServiceTask implements JavaDelegate{

	@Autowired
	private ITaskDataService taskDataService;
	
	@Autowired
	ModelFlowMapper modelFlowMapper;
	
	@Autowired
	private TaskService taskService;
	
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		String taskName = execution.getCurrentFlowElement().getName();
		//String proccessInstanceId = execution.getCurrentFlowElement().getId();
		String proccessInstanceId = ((ExecutionEntityImpl)execution).getProcessInstanceId();
		/*List<Task> taskList = taskService.createTaskQuery().processInstanceId(proccessInstanceId).active().list();
		for(Task task:taskList) {
			taskDataService.saveTasklist(task.getId(), proccessInstanceId, execution.getProcessDefinitionId().substring(0, execution.getProcessDefinitionId().indexOf(":")), task.getAssignee(), task.getName());
		}*/
		taskDataService.saveTasklist(execution.getId(), proccessInstanceId, execution.getProcessDefinitionId().substring(0, execution.getProcessDefinitionId().indexOf(":")), "sys", taskName);
		System.out.println("execute");
	}

}
