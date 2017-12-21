package com.elextec.mdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataPermissionDefined;
import com.elextec.mdm.mapper.DataPermissionDefinedMapper;
import com.elextec.mdm.mapper.DataPermissionMapper;

/**
 * @author zhangkj
 *
 */
@Service
public class DataPermissionService implements IDataPermissionService {

	@Autowired
	private DataPermissionMapper dataPermissionMapper;
	
	@Autowired
	private DataPermissionDefinedMapper dataPermissionDefinedMapper;

	@Override
	public VoResponse addDataPermissionDefined(DataPermissionDefined entity) {
		
		return null;
	}
	
	
}
