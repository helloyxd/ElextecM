package com.elextec.mdm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.service.IActivitiService;

@Service
@Transactional
public class ActivitiService implements IActivitiService{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	// 开始流程，传入申请者的id以及公司的id
	@Override
	public ProcessInstance startProcess() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("name", "yuxiaodong");
		variables.put("age", 20);
		ProcessInstance process = runtimeService.startProcessInstanceByKey("Expense01", variables);
		return process;
	}

	// 获得某个人的任务别表
	@Override
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().active().list();
		//return taskService.createTaskQuery().taskCandidateUser(assignee).list();
	}

	// 完成任务
	@Override
	public void completeTasks(String taskId) {
		System.out.println("test Variable:"+ taskService.getVariable(taskId, "name"));
		//runtimeService.getVariable(executionId, variableName);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		//taskVariables.put("name", "yuxiaodong");
		//taskVariables.put("age", 20);
		taskService.complete(taskId, taskVariables);
	}

}
