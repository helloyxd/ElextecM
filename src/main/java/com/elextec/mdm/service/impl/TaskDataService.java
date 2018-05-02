package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.entity.TaskDataRecordSummary;
import com.elextec.mdm.entity.TaskList;
import com.elextec.mdm.mapper.ModelFlowMapper;
import com.elextec.mdm.mapper.TaskDataRecordDetailMapper;
import com.elextec.mdm.mapper.TaskDataRecordSummaryMapper;
import com.elextec.mdm.mapper.TaskListMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.ITaskDataService;
import com.elextec.mdm.vo.VoMain;

@Service
public class TaskDataService extends BaseService implements ITaskDataService{

	@Autowired
	private TaskListMapper taskListMapper;
	
	@Autowired
	private TaskDataRecordSummaryMapper taskDataRecordSummaryMapper;
	
	@Autowired
	private TaskDataRecordDetailMapper taskDataRecordDetailMapper;
	
	@Autowired
	private IMdmModelService mdmModelService;
	
	@Autowired
	private ModelFlowMapper modelFlowMapper;

	@Override
	public List<TaskDataRecordSummary> getMainData() {
		List<TaskDataRecordSummary> list = taskDataRecordSummaryMapper.findAll();
		return list;
	}

	@Override
	public List<VoMain> getMainCount() {
		List<MdmModel> models = mdmModelService.getAll();
		List<MdmBs> bses = mdmModelService.getAllBs();
		List<Map<String,Object>>  syncs = taskDataRecordDetailMapper.findSyncCount();
		List<Map<String,Object>>  sends = taskDataRecordDetailMapper.findSendCount();
		System.out.println(syncs);
		System.out.println(sends);
		List<VoMain> list = new ArrayList<VoMain>();
		for(MdmModel model : models) {
			VoMain vo = new VoMain();
			vo.setName(model.getMdmModel());
			for(Map<String,Object> mapsync : syncs) {
				if(model.getId().equals(mapsync.get("MODEL"))) {
					vo.setCount(Integer.parseInt(mapsync.get("COUNT").toString()));
				}
			}
			Map<String ,Integer> children = new HashMap<String ,Integer>();
			for(MdmBs bs : bses) {
				
				int count = 0;
				for(Map<String,Object> mapsend : sends) {
					if(model.getId().equals(mapsend.get("MODEL")) && bs.getId().equals(mapsend.get("BS"))) {
						count = Integer.parseInt(mapsend.get("COUNT").toString());
					}
				}
				children.put(bs.getBsName(), count);
				
			}
			vo.setChildren(children);
			list.add(vo);
		}
		return list;
	}

	@Override
	public VoResponse saveTasklist(String flowId,String activityId,String currentUser,String currentNode) {
		VoResponse voRes = new VoResponse();
		TaskList entity = new TaskList();
		entity.setFlowId(flowId);
		entity.setCreater(getUserName());
		entity.setCurrentUser(currentUser);
		entity.setCurrentNode(currentNode);
		List<ModelFlow> list = modelFlowMapper.findByActivitiId(activityId);
		if(list.size() == 0) {
			voRes.setNull(voRes);
			voRes.setMessage("activityId未定义");
			return voRes;
		}
		ModelFlow flow = list.get(0);
		entity.setModelId(flow.getModelId());
		entity.setFlowType(flow.getOperationType());
		
		List<TaskList> tasks = taskListMapper.findByflowId(flowId);
		if(tasks.size() > 0) {
			entity.setLastUser(tasks.get(0).getCurrentUser());
			entity.setLastNode(tasks.get(0).getCurrentNode());
		}
		taskListMapper.insert(entity);
		return voRes;
	}

	@Override
	public List<TaskList> getAllTaskList() {
		List<TaskList> list = taskListMapper.findByCurrentUser(getUserName());
		return list;
	}

	@Override
	public List<TaskList> getAllTaskListDone() {
		List<TaskList> list = taskListMapper.findByLastUser(getUserName());
		return list;
	}
	
	
}
