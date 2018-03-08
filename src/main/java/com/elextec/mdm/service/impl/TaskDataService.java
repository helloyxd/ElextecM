package com.elextec.mdm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.entity.TaskDataRecordDetail;
import com.elextec.mdm.entity.TaskDataRecordSummary;
import com.elextec.mdm.mapper.TaskListMapper;
import com.elextec.mdm.service.ITaskDataService;

public class TaskDataService implements ITaskDataService{

	@Autowired
	private TaskListMapper taskListMapper;
	
	@Autowired
	private TaskDataRecordSummary taskDataRecordSummary;
	
	@Autowired
	private TaskDataRecordDetail taskDataRecordDetail;
	
	
}
