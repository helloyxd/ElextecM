package com.elextec.mdm.service;

import java.util.List;

//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;

public interface IActivitiService {
	//public ProcessInstance startProcess();
	//public List<Task> getTasks(String assignee);
	public void completeTasks(String taskId);
}
