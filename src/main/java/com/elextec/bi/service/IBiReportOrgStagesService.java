package com.elextec.bi.service;


import com.elextec.bi.entity.BiOrgStages;


import java.util.List;

public interface IBiReportOrgStagesService {

	public List<BiOrgStages> queryAllOrg();

	public List<BiOrgStages> queryByOrgId(String id);

	
}
