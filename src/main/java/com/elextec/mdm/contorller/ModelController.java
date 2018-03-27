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
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.ITableDDLService;

/**
 * @author zhangkj
 *
 */
@RestController
@RequestMapping("/mdm/model")
public class ModelController {

	@Autowired
	private IMdmModelService mdmModelService;
	
	@Autowired
	private ITableDDLService tableDDLService;
	
	//@Autowired
	//private IDataMapService dataMapService;
	
	@PostMapping
	public Object add(@RequestBody MdmModel model){
		return mdmModelService.addModel(model);
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id){
		return mdmModelService.delModel(id);
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
	
	@GetMapping("getAllInfo")
	public Object getAllInfo(){
		VoResponse voRes = new VoResponse();
		voRes.setData(mdmModelService.getAllInfo());
		return voRes;
	}
	
	@GetMapping("getAllBs")
	public Object getAllBs(){
		VoResponse voRes = new VoResponse();
		voRes.setData(mdmModelService.getAllBs());
		return voRes;
	}
	
	@DeleteMapping("delBs")
	public Object delBs(@RequestParam("id") String id){
		return mdmModelService.delBs(id);
	}
	
	@GetMapping
	public Object getMdmModelDetailById(@RequestParam("id") String id){
		VoResponse voRes = new VoResponse();
		MdmModel model = mdmModelService.getById(id);
		for(TableDefinition table : model.getTableDefinitions()){
			 tableDDLService.setColumnsDefinition(table);
			// dataMapService.setMdmTableMap(table);
		}
		voRes.setData(model);
		return voRes;
	}
	
	@GetMapping("getBs")
	public Object getBsDetailByBsId(@RequestParam("id") String id){
		VoResponse voRes = new VoResponse();
		voRes.setData(mdmModelService.getBsById(id));
		return voRes;
	}
}
