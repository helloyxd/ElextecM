package com.elextec.mdm.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.DataMapEnum;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmDataMap;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.ModelFlow;
import com.elextec.mdm.entity.ServiceInterfaceDefined;
import com.elextec.mdm.entity.ServiceInterfaceParam;
import com.elextec.mdm.entity.ServiceParamFieldDefined;
import com.elextec.mdm.entity.ServiceParamTableDefined;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.IMenuService;
import com.elextec.mdm.service.IServiceInterfaceDefinedService;
import com.elextec.mdm.service.IServiceParamTableDefinedService;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.service.impl.ModelFlowService;
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
	
	@Autowired
	private IServiceInterfaceDefinedService serviceInterfaceDefinedService;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private ModelFlowService modelFlowService;
	
	@DeleteMapping
	public Object del(@RequestBody List<VoDataMap> dataMaps) {
		VoResponse voRes = null;
		if(dataMaps != null && dataMaps.size() > 0){
			for(VoDataMap dataMap : dataMaps){
				voRes = dataMapService.del(dataMap);
			}
		}
		return voRes;
	}
	
	@PostMapping
	public Object add(@RequestBody List<VoDataMap> dataMaps) {
		VoResponse voRes = null;
		if(dataMaps != null && dataMaps.size() > 0){
			for(VoDataMap dataMap : dataMaps){
				voRes = dataMapService.save(dataMap);
			}
		}
		return voRes;
	}
	
	@PostMapping("saveAll")
	public Object addAll(@RequestBody List<VoDataMap> dataMaps) {
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
		for(TableDefinition table : model.getTableDefinitions()){
			tableDDLService.setColumnsDefinition(table);
		}
		voRes = dataMapService.getMdmTableMapById(model, bsId);
		
		return voRes;
	}
	
	/**
	 * 查询MDM模块下模型数据以及数据连线映射
	 * @param modelId 模块ID
	 * @param map 查询字段和查询值
	 * @param page 第几页
	 * @param pageSize 每页显示几条数据
	 * @param isSelect 查询是否连线的数据
	 * @param order 排序
	 * @return
	 */
	@GetMapping("getDataMapMdm")
	public Object getDataMapMdm(@RequestParam("modelId") String modelId,@RequestParam(value = "bsId",required=false) String bsId,
			@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "isSelect") boolean isSelect, @RequestParam(value = "order", defaultValue = "1")String order) {
		VoResponse voRes = new VoResponse();
		Map<String, Object>  map = new HashMap<String, Object>();
		MdmModel model = null;
		if(modelId != null && !modelId.equals("")){
			model = mdmModelService.getById(modelId);
			if(model == null){
				voRes.setNull(voRes);
				voRes.setMessage("mdm模块信息获取失败");
				return voRes;
			}
			if(model.getTableDefinitions() == null || model.getTableDefinitions().size() == 0){
				voRes.setNull(voRes);
				voRes.setMessage("mdm模块下未定义表模型");
				return voRes;
			}
			for(TableDefinition table : model.getTableDefinitions()){
				tableDDLService.setColumnsDefinition(table);
			}
			map.put("mdmtable", model.getTableDefinitions());
			String tableName = model.getTableDefinitions().get(0).getTableName();
			Map<String, String> queryParam = new HashMap<String, String>();
			Map<String, Object> mapmdm = null;
			if (bsId != null && !bsId.equals("")) {
				mapmdm = dataMapService.getPage(tableName, queryParam, modelId, bsId, page, pageSize, order, isSelect, true);
			}else{
				mapmdm = dataMapService.getPage(tableName, queryParam, page, pageSize, order);
			}
			map.put("mdmdata", mapmdm);
		}
		
		MdmBs bs = null;
		ServiceInterfaceParam siParam = null;
		if (bsId != null && !bsId.equals("")) {
			bs = mdmModelService.getBsByIdOnly(bsId);
			if(bs == null){
				voRes.setNull(voRes);
				voRes.setMessage("业务系统信息获取失败");
				return voRes;
			}
			siParam = serviceInterfaceDefinedService.getSiTable(modelId, bsId);
			if (siParam == null) {
				voRes.setNull(voRes);
				voRes.setMessage("mdm模型" + model.getMdmModel() + "获取业务系统数据表失败");
				return voRes;
			}
			map.put("bstable", siParam.getsParamTableDefineds());
			String tableName = siParam.getsParamTableDefineds().get(0).getTableName();
			Map<String, Object> mapbs = null;
			Map<String, String> queryParam = new HashMap<String, String>();
			if(modelId != null && !modelId.equals("")){
				mapbs = dataMapService.getPage(tableName, queryParam, modelId, bsId, page, pageSize, order, isSelect, false);
			}else{
				mapbs = dataMapService.getPage(tableName, queryParam, page, pageSize, order);
			}
			map.put("bsdata", mapbs);
		}
		voRes.setData(map);
		return voRes;
	}
	
	@PostMapping("saveDataMapMdm")
	public Object addDataMapMdm(@RequestBody List<VoDataMap> dataMaps) {
		VoResponse voRes = new VoResponse();
		if(dataMaps != null && dataMaps.size() > 0){
			String modelId = null;
			String bsId = null;
			List<MdmDataMap> list = null;
			MdmDataMap entity = null;
			for(VoDataMap dataMap : dataMaps){
				TableDefinition table = tableDDLService.getById(dataMap.getMdmTableId());
				modelId = table.getModelId();
				ServiceInterfaceDefined siDefined = serviceInterfaceDefinedService.getSiDefinedByTableId(dataMap.getBsTableId());
				bsId = siDefined.getBsId();
				list = new ArrayList<MdmDataMap>();
				for(VoLineData data : dataMap.getLineData()){
					entity = new MdmDataMap();
					entity.setModelId(modelId);
					entity.setBsId(bsId);
					entity.setMdmDataId(data.getSourceId());
					entity.setBsDataId(data.getTargetId());
					list.add(entity);
				}
				voRes = dataMapService.saveAll(list);
			}
		}
		return voRes;
	}
	
	@DeleteMapping("delDataMapMdm")
	public Object delDataMapMdm(@RequestBody List<VoDataMap> dataMaps) {
		VoResponse voRes = new VoResponse();
		if(dataMaps != null && dataMaps.size() > 0){
			String modelId = null;
			String bsId = null;
			List<MdmDataMap> list = null;
			MdmDataMap entity = null;
			for(VoDataMap dataMap : dataMaps){
				TableDefinition table = tableDDLService.getById(dataMap.getMdmTableId());
				modelId = table.getModelId();
				ServiceInterfaceDefined siDefined = serviceInterfaceDefinedService.getSiDefinedByTableId(dataMap.getBsTableId());
				bsId = siDefined.getBsId();
				list = new ArrayList<MdmDataMap>();
				for(VoLineData data : dataMap.getLineData()){
					entity = new MdmDataMap();
					entity.setModelId(modelId);
					entity.setBsId(bsId);
					entity.setMdmDataId(data.getSourceId());
					entity.setBsDataId(data.getTargetId());
					list.add(entity);
				}
				voRes = dataMapService.delAll(list);
			}
		}
		return voRes;
	}
	
	@PostMapping("sync")
	public Object syncToMdm(@RequestParam("menuId") String menuId) {
		VoResponse voRes = new VoResponse();
		Menu menu = menuService.getMenuById(menuId);
		if(menu != null) {
			MdmModel model = mdmModelService.getByName(menu.getMenuName());
			ModelFlow entity = modelFlowService.getByModelAndType(model.getId(), "0");
			if(entity == null) {
				voRes.setNull(voRes);
				voRes.setMessage(model.getMdmModel() + "未创建流程信息");
			}else {
				voRes = dataMapService.syncToMdm(model.getId(),entity.getActivitiId());
			}
			
		}else {
			voRes.setNull(voRes);
			voRes.setMessage("参数错误");
		}
		return voRes;
	}
	
	@PostMapping("send")
	public Object send(@RequestParam("menuId") String menuId) {
		VoResponse voRes = new VoResponse();
		Menu menu = menuService.getMenuById(menuId);
		if(menu != null) {
			MdmModel model = mdmModelService.getByName(menu.getMenuName());
			ModelFlow entity = modelFlowService.getByModelAndType(model.getId(), "1");
			if(entity == null) {
				voRes.setNull(voRes);
				voRes.setMessage(model.getMdmModel() + "未创建流程信息");
			}else {
				voRes = dataMapService.syncToMdm(model.getId(),entity.getActivitiId());
			}
			
		}else {
			voRes.setNull(voRes);
			voRes.setMessage("参数错误");
		}
		return voRes;
	}
}
