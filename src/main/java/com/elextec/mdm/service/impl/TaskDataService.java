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
import com.elextec.mdm.entity.TaskDataRecordSummary;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.TaskDataRecordDetailMapper;
import com.elextec.mdm.mapper.TaskDataRecordSummaryMapper;
import com.elextec.mdm.mapper.TaskListMapper;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.ITaskDataService;
import com.elextec.mdm.vo.VoMain;

@Service
public class TaskDataService implements ITaskDataService{

	@Autowired
	private TaskListMapper taskListMapper;
	
	@Autowired
	private TaskDataRecordSummaryMapper taskDataRecordSummaryMapper;
	
	@Autowired
	private TaskDataRecordDetailMapper taskDataRecordDetailMapper;
	
	@Autowired
	private IMdmModelService mdmModelService;

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
	
	
}
