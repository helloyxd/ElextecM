package com.elextec.bi.service.impl;

import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.VoResult;
import com.elextec.bi.common.entity.constant.UserStatusEnum;
import com.elextec.bi.entity.BiReportColConf;
import com.elextec.bi.entity.BiUser;
import com.elextec.bi.mapper.BiReportColConfMapper;
import com.elextec.bi.mapper.BiUserMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiReportColConfService;
import com.elextec.bi.service.IBiUserService;
import com.elextec.bi.utils.MD5Util;
import com.elextec.mdm.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Primary
@Service
public class BiReportColConfService extends BiBaseService implements IBiReportColConfService{

	@Autowired
	private BiReportColConfMapper biReportColConfMapper;
	
	@Override
	public BiReportColConf queryByReportName(String reportName){
		return biReportColConfMapper.queryByReportName(reportName);
	}

}
