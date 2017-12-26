package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataPermissionDefined;

/**
 * @author zhangkj
 *
 */
public interface IDataPermissionService {

	VoResponse addDataPermissionDefined(DataPermissionDefined dataPermissionDefined);
	
	List<DataPermissionDefined> getAll();
	
	VoResponse delDataPermission(String id);
	
	VoResponse delDataPermissionDefined(String id);
}
