
package com.elextec.mdm.activity;

import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;

public interface IBpmFileService {
	public VoResponse download(Map<String,String> param);
	
	public VoResponse saveBpm(Map<String, String> param);
	
	public VoResponse deleteBpm(String processId);
	
	public VoResponse findByModel(String modelId);
	
}




