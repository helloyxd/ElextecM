package com.elextec.bi.controller;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiDataSource;
import com.elextec.bi.service.IBiDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bi/dataSource")
public class BiDataSourceController {

	@Autowired
	private IBiDataSourceService biDataSourceService;

	@GetMapping("/getAll")
	public Object getAll() {
		VoResponse voRes = new VoResponse();
		voRes.setData(biDataSourceService.queryAll());
		return voRes;
	}

	@RequestMapping(value = "/addInfo", method = RequestMethod.POST)
	public Object add(@RequestBody BiDataSource biDataSource) {
		VoResponse voRes = biDataSourceService.save(biDataSource);
		return voRes;
	}

	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public Object queryById(String id) {
		VoResponse voRes = new VoResponse();
		BiDataSource biDataSource = biDataSourceService.queryById(id);
		if(biDataSource != null){
			voRes.setData(biDataSource);
		}else{
			voRes.setFail(voRes);
			voRes.setMessage("dataSource is null");
		}
		return voRes;
	}

	@RequestMapping(value ="/delInfo",method = RequestMethod.POST)
	public Object del(String id){
		return biDataSourceService.delete(id);
	}

	@RequestMapping(value ="/updateInfo",method = RequestMethod.POST)
	public Object update(@RequestBody BiDataSource biDataSource){
		return biDataSourceService.update(biDataSource);
	}

}
