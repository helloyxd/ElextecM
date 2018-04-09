package com.elextec.mdm.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.DataMapEnum;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.entity.ServiceParamFieldDefined;
import com.elextec.mdm.entity.ServiceParamTableDefined;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.IServiceParamTableDefinedService;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.vo.VoDataMap;
import com.elextec.mdm.vo.VoLineData;

@RestController
@RequestMapping("/mdm/dataMap")
public class DataMapController {

	@Autowired
	private IDataMapService dataMapService;
	
	@Autowired
	private ITableDDLService tableDDLService;
	
	@Autowired
	private IServiceParamTableDefinedService serviceParamTableDefinedService;
	
	@Autowired
	private IMdmModelService mdmModelService;
	
	@PostMapping
	public Object add(@RequestBody List<VoDataMap> dataMaps) {
		List<MdmTableMap> list = new ArrayList<MdmTableMap>();
		if(dataMaps != null && dataMaps.size() > 0){
			for(VoDataMap dataMap : dataMaps){
				TableDefinition mdmTable = tableDDLService.getById(dataMap.getMdmTableId());
				tableDDLService.setColumnsDefinition(mdmTable);
				ServiceParamTableDefined spTable = serviceParamTableDefinedService.getById(dataMap.getBsTableId());
				MdmTableMap tableMap = null;
				first : for(VoLineData line : dataMap.getLineData()){
					for(MdmTableMap entity : list){
						if(entity.getMdmFieldId().equals(line.getSourceId()) || entity.getBsFieldId().equals(line.getSourceId())){
							continue first;
						}
					}
					tableMap = new MdmTableMap();
					tableMap.setMdmTableId(dataMap.getMdmTableId());
					tableMap.setBsTableId(dataMap.getBsTableId());
					boolean flag = false;
					for(ColumnDefinition field : mdmTable.getColumnDefinitions()){
						if(field.getName().equals(line.getSourceId())){
							flag = true;
							tableMap.setMdmFieldId(line.getSourceId());
							tableMap.setBsFieldId(line.getTargetId());
							tableMap.setBsIoType(DataMapEnum.mdmSource);
							for(VoLineData line2 : dataMap.getLineData()){
								if(line2.getSourceId().equals(line.getTargetId())){
									tableMap.setBsIoType(DataMapEnum.allSource);
								}
							}
						}
					}
					if(!flag){
						for(ServiceParamFieldDefined field : spTable.getsParamFieldDefineds()){
							if(field.getId().equals(line.getSourceId())){
								tableMap.setBsFieldId(line.getSourceId());
								tableMap.setMdmFieldId(line.getTargetId());
								tableMap.setBsIoType(DataMapEnum.bsSource);
								for(VoLineData line2 : dataMap.getLineData()){
									if(line2.getSourceId().equals(line.getTargetId())){
										tableMap.setBsIoType(DataMapEnum.allSource);
									}
								}
							}
						}
					}
					flag = false;
					list.add(tableMap);
				}
				
			}
		}
		VoResponse voRes = dataMapService.saveAll(list, dataMaps);
		return voRes;
	}
	
	@GetMapping
	public Object get(@RequestParam("modelId") String modelId, @RequestParam("bsId") String bsId) {
		VoResponse voRes = new VoResponse();
		MdmModel model = mdmModelService.getById(modelId);
		if(model == null || model.getTableDefinitions() == null || model.getTableDefinitions().size() == 0){
			voRes.setNull(voRes);
			voRes.setMessage("mdm模块信息获取失败");
			return voRes;
		}
		tableDDLService.setColumnsDefinition(model.getTableDefinitions().get(0));
		voRes = dataMapService.getMdmTableMapById(model, bsId);
		
		return voRes;
	}
	
	@PutMapping("sync")
	public Object sync(@RequestParam("modelId") String modelId, @RequestParam("bsId") String bsId) {
		MdmModel model = mdmModelService.getById(modelId);
		MdmBs bs = mdmModelService.getBsById(bsId);
		List<MdmTableMap> list = dataMapService.getById(model.getTableDefinitions().get(0).getId(), bs.getSiDefineds().get(0).getSiParams().get(0).getsParamTableDefineds().get(0).getId());
		return dataMapService.syncToMdm(model, bs, list, "");
	}
	
	@PutMapping("send")
	public Object send(@RequestParam("modelId") String modelId, @RequestParam("bsId") String bsId) {
		MdmModel model = mdmModelService.getById(modelId);
		MdmBs bs = mdmModelService.getBsById(bsId);
		List<MdmTableMap> list = dataMapService.getById(model.getTableDefinitions().get(0).getId(), bs.getSiDefineds().get(0).getSiParams().get(0).getsParamTableDefineds().get(0).getId());
		return dataMapService.syncToMdm(model, bs, list, "");
	}
}
