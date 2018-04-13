package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ServiceInterfaceDefined;
import com.elextec.mdm.service.IServiceInterfaceDefinedService;
import com.elextec.mdm.service.IServiceInterfaceParamService;
import com.elextec.mdm.service.IServiceParamFieldDefinedService;
import com.elextec.mdm.service.IServiceParamTableDefinedService;

@RestController
@RequestMapping("/mdm/serviceInterface")
public class ServiceInterfaceController {

	@Autowired
	private IServiceInterfaceDefinedService serviceInterfaceDefinedService;
	
	@Autowired
	private IServiceInterfaceParamService serviceInterfaceParamService;
	
	@Autowired
	private IServiceParamFieldDefinedService serviceParamFieldDefinedService;
	
	@Autowired
	private IServiceParamTableDefinedService serviceParamTableDefinedService;
	
	@GetMapping("getAll")
	public Object getAll() {
		VoResponse voRes = new VoResponse();
		voRes.setData(serviceInterfaceDefinedService.getAll());
		return voRes;
	}
	
	@GetMapping("getPage")
	public Object getPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "bsId", required = false) String bsId,
			@RequestParam(value = "modelId", required = false) String modelId) {
		VoResponse voRes = new VoResponse();
		ServiceInterfaceDefined entity = new ServiceInterfaceDefined();
		entity.setBsId(bsId);
		entity.setModelId(modelId);
		voRes.setData(serviceInterfaceDefinedService.getPage(entity, page, pageSize));
		return voRes;
	}
	
	@PostMapping
	public Object add(@RequestBody ServiceInterfaceDefined entity) {
		VoResponse voRes = new VoResponse();
		voRes = serviceInterfaceDefinedService.addOrUpdate(entity);
		return voRes;
	}

	@PutMapping
	public Object update(@RequestBody ServiceInterfaceDefined entity) {
		VoResponse voRes = new VoResponse();
		voRes = serviceInterfaceDefinedService.addOrUpdate(entity);
		return voRes;
	}
	
	public Object del(@RequestParam("id") String id) {
		VoResponse voRes = new VoResponse();
		voRes = serviceInterfaceDefinedService.del(id);
		return voRes;
	}
	
	@GetMapping
	public Object getServiceInterfaceDefinedbyId(@RequestParam("id") String id) {
		VoResponse voRes = new VoResponse();
		voRes.setData(serviceInterfaceDefinedService.getById(id));
		return voRes;
	}

	@GetMapping("getSIParam")
	public Object getServiceInterfaceParam() {
		VoResponse voRes = new VoResponse();
		voRes.setData(serviceInterfaceParamService.getAll());
		return voRes;
	}
	
	@GetMapping("getSPFieldDefined")
	public Object getServiceParamFieldDefined(@RequestParam("id") String siId) {
		VoResponse voRes = new VoResponse();
		voRes.setData(serviceParamFieldDefinedService.getById(siId));
		return voRes;
	}
	
	@GetMapping("getSPTableDefined")
	public Object getServiceParamTableDefined() {
		VoResponse voRes = new VoResponse();
		voRes.setData(serviceParamTableDefinedService.getAll());
		return voRes;
	}
}
