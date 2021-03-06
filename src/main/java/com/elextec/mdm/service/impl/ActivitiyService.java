package com.elextec.mdm.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.entity.TaskList;
import com.elextec.mdm.service.IActivitiService;
import com.elextec.mdm.service.IModelFlowService;
import com.elextec.mdm.service.ITaskDataService;
@Service
public class ActivitiyService implements IActivitiService{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	IModelFlowService modelFlowService;
	
	@Autowired
	private ITaskDataService taskDataService;
	

	
	//开始流程，根据传入的modelId,启动流程
	public void startProcessByModel(String modelId,String type) {
		ModelFlow modelFlow = modelFlowService.getByModelAndType(modelId, type);
		if(modelFlow != null) {
			String processId = modelFlow.getActivitiId();
			ProcessInstance process = this.startProcess(processId);
			List<Task> taskList = getAllActiveTasks(process.getId());
			for(Task task:taskList) {
				taskDataService.saveTasklist(task.getId(), process.getId(), processId, task.getAssignee(), task.getName());
			}
		}
	}
	
	
	// 开始流程，传入申请者的id以及公司的id
	@Override
	public ProcessInstance startProcess(String processId) {
		ProcessInstance process = runtimeService.startProcessInstanceByKey(processId);
		//return process;process
		return process;
	}

	// 获得某个人的任务别表
	@Override
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskCandidateUser(assignee).active().list();
	}
	
	
	public List<Task> getAllActiveTasks(String processId) {
		return taskService.createTaskQuery().processInstanceId(processId).active().list();
	}
	
	public void completeAllTasks() {
		List<Task> taskList = taskService.createTaskQuery().active().list();
		if(taskList!=null && taskList.size()>0) {
			for(Task task:taskList) {
				List<TaskList> mytasks = taskDataService.getByTaskId(task.getId());
				taskService.complete(task.getId());
				taskDataService.updateTasklist(mytasks.get(0), task.getOwner(), task.getName());
			}
		}
	}

	// 完成任务
	@Override
	public void completeTasks(String taskId) {
		taskService.complete(taskId);
	}


}
