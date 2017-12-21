package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.service.IMdmModelService;

/**
 * @author zhangkj
 *
 */
@RestController
@RequestMapping("model")
public class MdmModelController {

	@Autowired
	private IMdmModelService mdmModelService;
	
	@PostMapping
	public Object add(@RequestBody MdmModel model){
		return mdmModelService.addModel(model);
	}
	
	@PostMapping("addBs")
	public Object addBs(@RequestBody MdmBs bs){
		return mdmModelService.addBs(bs);
	}
	
	@GetMapping("getAll")
	public Object getAll(){
		VoResponse voRes = new VoResponse();
		voRes.setData(mdmModelService.getAll());
		return voRes;
	}
	
	
	
}
