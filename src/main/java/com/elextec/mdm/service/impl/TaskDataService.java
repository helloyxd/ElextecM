package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.TaskDataRecordSummary;
import com.elextec.mdm.mapper.TaskDataRecordDetailMapper;
import com.elextec.mdm.mapper.TaskDataRecordSummaryMapper;
import com.elextec.mdm.mapper.TaskListMapper;
import com.elextec.mdm.service.ITaskDataService;

@Service
public class TaskDataService implements ITaskDataService{

	@Autowired
	private TaskListMapper taskListMapper;
	
	@Autowired
	private TaskDataRecordSummaryMapper taskDataRecordSummaryMapper;
	
	@Autowired
	private TaskDataRecordDetailMapper taskDataRecordDetailMapper;

	@Override
	public VoResponse getMainData() {
		VoResponse voRes = new VoResponse();
		List<TaskDataRecordSummary> list = taskDataRecordSummaryMapper.findAll();
		voRes.setData(list);
		return voRes;
	}
	
	
}
