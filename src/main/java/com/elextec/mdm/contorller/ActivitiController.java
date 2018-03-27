package com.elextec.mdm.contorller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.activity.IBpmFileService;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.service.IActivitiService;

@RestController
@RequestMapping("/mdm/flow")
public class ActivitiController {
	
	@Autowired
	private IActivitiService activitiyService;
/*	
	@Autowired
	private IActivitiTestService activitiTestyService;
	
	@Autowired
	private IMdmModelService mdmModelService;*/
	@Autowired
	private IBpmFileService bpmFileService;
	
	
	@PostMapping("publish")
	public VoResponse publishBpm(Map map) throws Exception{
		return bpmFileService.download(map);
	}
	
	@PostMapping("saveBpm")
	public VoResponse saveBpm(Map map) throws Exception{
		return bpmFileService.download(map);
	}
	
	
	@DeleteMapping("deleteBpm")
	public VoResponse deleteBpm(@RequestParam("processId") String processId) throws Exception{
		return bpmFileService.deleteBpm(processId);
	}
	
}
