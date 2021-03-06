package com.elextec.mdm.service;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;

public interface IActivitiService {
	public void startProcessByModel(String modelId,String type);
	public ProcessInstance startProcess(String processId);
	public List<Task> getTasks(String assignee);
	public void completeTasks(String taskId);
	public void completeAllTasks();
}
