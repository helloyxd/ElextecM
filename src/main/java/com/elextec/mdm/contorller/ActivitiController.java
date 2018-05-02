package com.elextec.mdm.contorller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//@Autowired
	//private IActivitiTestService activitiTestyService;
	
	/*@Autowired
	private IMdmModelService mdmModelService;*/
	@Autowired
	private IBpmFileService bpmFileService;
	
	
	@GetMapping("startProcess")
	public VoResponse startProcess() {
		VoResponse voResponse = new VoResponse();
		activitiyService.startProcess("yutest");
		//activitiyService.completeAllTasks(); //service task会自动完成任务，人工任务才需要使用complete
		return voResponse;
	}
	
	
	@PostMapping("publish")
	public VoResponse publishBpm(@RequestBody Map map) throws Exception{
		return bpmFileService.download(map);
	}
	
	@PostMapping("saveBpm")
	public VoResponse saveBpm(@RequestBody Map map) throws Exception{
		return bpmFileService.saveBpm(map);
	}
	
	
	@DeleteMapping("deleteBpm")
	public VoResponse deleteBpm(@RequestParam("processId") String processId) throws Exception{
		return bpmFileService.deleteBpm(processId);
	}
	
	@GetMapping("findByModelId")
	public VoResponse findByModelId(@RequestParam("modelId") String modelId) {
		return bpmFileService.findByModel(modelId);
	}
	
	@GetMapping("getAllFlowData")
	public VoResponse getAllFlowData() {
		return bpmFileService.getAllFlowData();
	}
	
	
}
