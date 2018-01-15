package com.elextec.bi.controller;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiDataSource;
import com.elextec.bi.entity.BiReportConfig;
import com.elextec.bi.service.IBiDataSourceService;
import com.elextec.bi.service.IBiReportConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bi/reportConfig")
public class BiReportConfigController {

	@Autowired
	private IBiReportConfigService biReportConfigService;

	@GetMapping("/getAll")
	public Object getAll() {
		VoResponse voRes = new VoResponse();
		voRes.setData(biReportConfigService.findAll());
		return voRes;
	}

	@RequestMapping(value = "/addInfo", method = RequestMethod.POST)
	public Object add(@RequestBody BiReportConfig biReportConfig) {
		VoResponse voRes = biReportConfigService.addReportConfig(biReportConfig);
		return voRes;
	}

	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public Object queryById(String id) {
		VoResponse voRes = new VoResponse();
		BiReportConfig biReportConfig = biReportConfigService.findById(id);
		if(biReportConfig != null){
			voRes.setData(biReportConfig);
		}else{
			voRes.setFail(voRes);
			voRes.setMessage("reportConfig is null");
		}
		return voRes;
	}

	@RequestMapping(value ="/delInfo",method = RequestMethod.POST)
	public Object del(String id){
		return biReportConfigService.delReportConfig(id);
	}

	@RequestMapping(value ="/updateInfo",method = RequestMethod.POST)
	public Object update(@RequestBody BiReportConfig biReportConfig){
		return biReportConfigService.updateReportConfig(biReportConfig);
	}

}
