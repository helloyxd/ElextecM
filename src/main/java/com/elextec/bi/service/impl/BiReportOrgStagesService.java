package com.elextec.bi.service.impl;


import com.elextec.bi.entity.BiOrgStages;
import com.elextec.bi.mapper.BiReportOrgStagesMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiReportOrgStagesService;
import com.elextec.bi.service.IBiRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BiReportOrgStagesService extends BiBaseService implements IBiReportOrgStagesService {

	@Autowired
	private BiReportOrgStagesMapper biReportOrgStagesMapper;

	public List<BiOrgStages> queryAllOrg(){
		return biReportOrgStagesMapper.queryAllOrg();
	}

	public List<BiOrgStages> queryByOrgId(String id){
		return biReportOrgStagesMapper.queryByOrgId(id);
	}

	
}
