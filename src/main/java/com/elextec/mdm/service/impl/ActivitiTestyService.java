package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmBsMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IActivitiTestService;
import com.elextec.mdm.service.IMdmModelService;

/**
 * @author zhangkj
 *
 */
@Service
public class ActivitiTestyService implements IActivitiTestService {

	
	

	@Override
	public void startProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTasks(String assignee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completeTasks(String taskId) {
		// TODO Auto-generated method stub
		
	}

}
