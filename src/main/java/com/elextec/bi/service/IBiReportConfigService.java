package com.elextec.bi.service;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiReportConfig;

import java.util.List;

public interface IBiReportConfigService {

	List<BiReportConfig> findAll();

	BiReportConfig findById(String id);

	VoResponse delReportConfig(String id);

	VoResponse addReportConfig(BiReportConfig biReportConfig);

	VoResponse updateReportConfig(BiReportConfig biReportConfig);
	
//	VoResponse addReportDataPermission(String reportId,String[] dataPermissions);
//
//	VoResponse updateReportDataPermission(String reportId,String[] dataPermissions);
}
