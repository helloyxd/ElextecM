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

import com.elextec.mdm.service.IActivitiService;
@Service
public class ActivitiyService implements IActivitiService{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	

	// 开始流程，传入申请者的id以及公司的id
	
	@Override
	public ProcessInstance startProcess(String processId) {
		
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("name", "yuxiaodong");
		variables.put("age", 20);
		ProcessInstance process = runtimeService.startProcessInstanceByKey(processId, variables);
		//return process;
		return null;
	}

	// 获得某个人的任务别表
	@Override
	public List<Task> getTasks(String assignee) {
		//return taskService.createTaskQuery().active().list();
		//return taskService.createTaskQuery().taskCandidateUser(assignee).list();
		return null;
	}

	// 完成任务
	@Override
	public void completeTasks(String taskId) {
		//System.out.println("test Variable:"+ taskService.getVariable(taskId, "name"));
		//runtimeService.getVariable(executionId, variableName);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		//taskVariables.put("name", "yuxiaodong");
		//taskVariables.put("age", 20);
		//taskService.complete(taskId, taskVariables);
		taskVariables.put("name", "yuxiaodong");
		taskVariables.put("age", 20);
		//taskService.complete(taskId, taskVariables);
	}

}
