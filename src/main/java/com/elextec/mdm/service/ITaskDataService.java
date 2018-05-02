package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.TaskDataRecordSummary;
import com.elextec.mdm.entity.TaskList;
import com.elextec.mdm.vo.VoMain;

public interface ITaskDataService {

	public List<TaskDataRecordSummary> getMainData();
	
	public List<VoMain> getMainCount();
	
	public VoResponse saveTasklist(String flowId,String activityId,String currentUser,String currentNode);
	
	public List<TaskList> getAllTaskList();
	
	public List<TaskList> getAllTaskListDone();
}
