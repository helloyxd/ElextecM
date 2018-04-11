package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmConfig;
import com.elextec.mdm.mapper.MdmConfigMapper;

@RestController
@RequestMapping("/mdm")
public class MyController {

	@Autowired
	private MdmConfigMapper mdmConfigMapper;
	
	@GetMapping
	public Object hello() {
		String msg = "Hello,MDM system";
		return msg;
	}

	@GetMapping("getMdmConfig")
	public Object getMdmConfig() {
		VoResponse voRes= new VoResponse();
		voRes.setData( mdmConfigMapper.findAll());
		return voRes;
	}
	
	@PostMapping("getMdmConfig")
	public Object mdmConfig(@RequestBody MdmConfig config){
		mdmConfigMapper.insert(config);
		return new VoResponse();
	}
	
	@DeleteMapping("getMdmConfig")
	public Object mdmConfig(@RequestParam("id") String id){
		mdmConfigMapper.del(id);
		return new VoResponse();
	}
	
}
