package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataPermissionDefined;
import com.elextec.mdm.service.IDataPermissionService;

/**
 * @author zhangkj
 *
 */
@RestController
@RequestMapping("dataPermission")
public class DataPermissionController {

	@Autowired
	private IDataPermissionService dataPermissionService;
	
	@GetMapping("getAll")
	public Object getAll(){
		VoResponse voRes  = new VoResponse();
		voRes.setData(dataPermissionService.getAll());		
		return voRes;
	}
	
	@PostMapping
	public Object add(@RequestBody DataPermissionDefined defined){
		VoResponse voRes  = dataPermissionService.addDataPermissionDefined(defined);
		return voRes;
	}
}
