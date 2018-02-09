package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataStructureMap;
import com.elextec.mdm.service.IDataStructureMapService;

@RestController
@RequestMapping("/mdm/dataMap")
public class DataMapController {

	@Autowired
	private IDataStructureMapService dataStructureMapService;
	
	@PostMapping
	public Object add(@RequestBody DataStructureMap dataStructureMap) {
		VoResponse voRes = dataStructureMapService.save(dataStructureMap);
		return voRes;
	}
	
	@GetMapping
	public Object hello() {
		String msg = "Hello,MDM system";
		return msg;
	}

}
