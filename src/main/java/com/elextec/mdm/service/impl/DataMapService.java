package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.DataMapEnum;
import com.elextec.mdm.common.entity.constant.SIParamEnum;
import com.elextec.mdm.common.entity.constant.TaskDataRecordStateEnum;
import com.elextec.mdm.common.entity.constant.TaskTypeEnum;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmDataMap;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.entity.ServiceInterfaceDefined;
import com.elextec.mdm.entity.ServiceInterfaceParam;
import com.elextec.mdm.entity.ServiceParamFieldDefined;
import com.elextec.mdm.entity.ServiceParamTableDefined;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.entity.TaskDataRecordDetail;
import com.elextec.mdm.entity.TaskDataRecordSummary;
import com.elextec.mdm.mapper.MdmBsMapper;
import com.elextec.mdm.mapper.MdmDataMapMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.MdmTableMapMapper;
import com.elextec.mdm.mapper.ServiceInterfaceDefinedMapper;
import com.elextec.mdm.mapper.ServiceParamFieldDefinedMapper;
import com.elextec.mdm.mapper.ServiceParamTableDefinedMapper;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.mapper.TaskDataRecordDetailMapper;
import com.elextec.mdm.mapper.TaskDataRecordSummaryMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.vo.VoDataMap;

@Service
public class DataMapService extends BaseService implements IDataMapService{

	@Autowired
	private MdmDataMapMapper dataMapMapper;
	
	@Autowired
	private MdmTableMapMapper tableMapMapper;
	
	@Autowired
	private TableDDLMapper tableDDLMapper;
	
	@Autowired
	private ServiceInterfaceDefinedMapper serviceInterfaceDefinedMapper;
	
	@Autowired
	private ServiceParamTableDefinedMapper serviceParamTableDefinedMapper;
	
	@Autowired
	private ServiceParamFieldDefinedMapper serviceParamFieldDefinedMapper;
	
	@Autowired
	private TaskDataRecordSummaryMapper taskDataRecordSummaryMapper;
	
	@Autowired
	private TaskDataRecordDetailMapper taskDataRecordDetailMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	@Autowired
	private MdmBsMapper mdmBsMapper;
	
	public void serviceExecute(MdmModel model, MdmBs bs, String processId){
        //在这里开启线程，执行操作
        ThreadExample te = new ThreadExample(model, bs, processId);
        te.start();
    }
	
	//内部类
    private class ThreadExample extends Thread{
    	private MdmModel model;
    	private MdmBs bs;
    	private String processId;
    	
		public ThreadExample(MdmModel model, MdmBs bs, String processId){
            //也可以在构造函数中传入参数
        	this.model = model;
        	this.bs = bs;
        	this.processId = processId;
        }
        public void run(){
            //这里为线程的操作
            //就可以使用注入之后Bean了
        	syncToMdm(model, bs, processId);
        }
    }
	
	@Override
	public VoResponse save(VoDataMap dataMap) {
		VoResponse voRes = new VoResponse();
		MdmTableMap tableMap = new MdmTableMap();
		tableMap.setCreater(getUserName());
		tableMap.setMdmTableId(dataMap.getMdmTableId());
		tableMap.setBsTableId(dataMap.getBsTableId());
		String sourceId = dataMap.getLineData().get(0).getSourceId();
		MdmTableMap entity = tableMapMapper.findByTableIdAndField(dataMap.getBsTableId(), dataMap.getMdmTableId(), sourceId);
		if(entity != null){
			if(entity.getMdmFieldId().equals(sourceId) && entity.getBsIoType().equals(DataMapEnum.bsSource)){
				entity.setBsIoType(DataMapEnum.allSource);
			}else if(entity.getBsFieldId().equals(sourceId) && entity.getBsIoType().equals(DataMapEnum.mdmSource)){
				entity.setBsIoType(DataMapEnum.allSource);
			}else{
				return voRes;
			}
			tableMapMapper.update(entity);
		}else{
			//判断是mdm到bs字段还是相反
			ServiceParamFieldDefined spField = serviceParamFieldDefinedMapper.findById(sourceId);
			if(spField == null){
				tableMap.setBsIoType(DataMapEnum.mdmSource);
			}else{
				tableMap.setBsIoType(DataMapEnum.bsSource);
			}
			tableMapMapper.insert(tableMap);
		}
		return voRes;
	}

	@Override
	public VoResponse del(VoDataMap dataMap) {
		VoResponse voRes = new VoResponse();
		String sourceId =  dataMap.getLineData().get(0).getSourceId();
		//String targetId =  dataMap.getLineData().get(0).getTargetId();
		MdmTableMap entity = tableMapMapper.findByTableIdAndField(dataMap.getBsTableId(), dataMap.getMdmTableId(), sourceId);
		if(entity.equals(DataMapEnum.allSource)){
			if(sourceId.equals(entity.getMdmFieldId())){
				entity.setBsIoType(DataMapEnum.bsSource);
			}else{
				entity.setBsIoType(DataMapEnum.mdmSource);
			}
			tableMapMapper.update(entity);
		}else{
			tableMapMapper.del(entity.getId());
		}
		return voRes;
	}

	@Override
	public VoResponse update(MdmTableMap tableMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MdmTableMap> getById(String modelid, String bsid) {
		return tableMapMapper.findByTableId(bsid, modelid);
	}

	@Override
	public VoResponse saveAll(List<MdmTableMap> list, List<VoDataMap> dataMaps) {
		VoResponse voRes = new VoResponse();
		String username = getUserName();
		for(VoDataMap dataMap : dataMaps){
			tableMapMapper.delByTableId(dataMap.getBsTableId(), dataMap.getMdmTableId());
		}
		for(MdmTableMap entity : list){
			entity.setCreater(username);
			tableMapMapper.insert(entity);
		}
		return voRes;
	}

	@Override
	public VoResponse syncToMdm(MdmModel model, MdmBs bs, String processId) {
		VoResponse voRes = new VoResponse();
		List<TableDefinition> mdmtables = model.getTableDefinitions();
		//模块下获取自定义表
		TableDefinition mdmtable = mdmtables.get(0);
		List<ServiceInterfaceDefined> siDefineds = bs.getSiDefineds();
		ServiceInterfaceDefined siDefined = siDefineds.get(0);
		List<ServiceInterfaceParam> siParams = siDefined.getSiParams();
		ServiceInterfaceParam siParam = null;
		for(ServiceInterfaceParam entity : siParams){
			if(entity.getIoType().equals(SIParamEnum.paramOut)){
				siParam = entity;
				break;
			}
		}
		//获取映射关系
		List<MdmTableMap> listMap = tableMapMapper.findByTableIdAndType(siParam.getsParamTableDefineds().get(0).getId(), mdmtable.getId(), DataMapEnum.bsSource, DataMapEnum.allSource);
		if(listMap == null || listMap.size() == 0){
			voRes.setNull(voRes);
			voRes.setMessage("映射关系未定义");
			return voRes;
		}
		//获取映射字段
		StringBuffer queryFieldMdm = new StringBuffer();
		StringBuffer queryFieldBs = new StringBuffer();
		for(MdmTableMap tableMap : listMap){
			queryFieldBs.append(tableMap.getSpFieldDefined().getFieldName()).append(",");
			queryFieldMdm.append(tableMap.getMdmFieldId()).append(",");
		}
		queryFieldBs.deleteCharAt(queryFieldBs.length() - 1);
		queryFieldMdm.deleteCharAt(queryFieldMdm.length() - 1);
		//System.out.println(queryFieldBs);
		//System.out.println(queryFieldMdm);
		//业务系统下表数据
		List<Map<String,Object>> listBs = tableDDLMapper.findTableField(siParam.getTableName(), queryFieldBs.toString(), "1=1");
		//MDM模块下自定义表的数据
		List<Map<String,Object>> listMdm = tableDDLMapper.findTableField(mdmtable.getTableName(), queryFieldMdm.toString(), "1=1");
		
		List<Map<String,Object>> listnew = new ArrayList<Map<String,Object>>();
		boolean flag;
		//遍历，根据映射字段，判断字段类型，
		for(Map<String,Object> mapbs : listBs){
			flag = false;
			//System.out.println(mapbs);
			second:for(Map<String,Object> mapmdm : listMdm){
				//System.out.println(mapmdm);
				for(MdmTableMap tableMap : listMap){
					if(mapbs.get(tableMap.getSpFieldDefined().getFieldName().toUpperCase()).equals(mapmdm.get(tableMap.getMdmFieldId().toUpperCase())) ){
						//值不相等，
						flag = false;
						continue second;
					}
					flag = true;
				}
			}
			if(!flag){
				listnew.add(mapbs);
			}
		}
		
		//记录同步日志
		TaskDataRecordSummary summary = new TaskDataRecordSummary();
		String userName = getUserName();
		summary.setCreater(userName);
		summary.setModelId(model.getId());
		summary.setTaskType(TaskTypeEnum.typePull);
		summary.setBsId(bs.getId());
		if(listnew.size() == 0){
			summary.setSuccessNum(0);
			summary.setFailNum(0);
			summary.setStatus(TaskDataRecordStateEnum.taskStateSuccess2);
			taskDataRecordSummaryMapper.insert(summary);
			voRes.setMessage("没有同步的数据");
			return voRes;
		}
		
		StringBuffer sb = new StringBuffer();
		//sb.append("INSERT ALL ");
		sb.append("INTO ").append(mdmtable.getTableName().toLowerCase()).append("(id,");
		for(MdmTableMap tableMap : listMap){
			sb.append(tableMap.getMdmFieldId()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(")");
		sb.append("VALUES(sys_guid(),");
		StringBuffer sb1 = new StringBuffer();
		TaskDataRecordDetail detail = null;
		for(Map<String,Object> map : listnew){
			sb1.append(sb);
			//System.out.println(map);
			for(MdmTableMap tableMap : listMap){
				detail = new TaskDataRecordDetail();
				detail.setCreater(userName);
				detail.setDataId(map.get("id").toString());
				detail.setFlowId(processId);
				detail.setModelId(model.getId());
				taskDataRecordDetailMapper.insert(detail);
				//System.out.println(tableMap.getSpFieldDefined().getFieldName());
				//System.out.println(map.get(tableMap.getSpFieldDefined().getFieldName()));
				sb1.append("'").append(map.get(tableMap.getSpFieldDefined().getFieldName().toUpperCase())).append("'").append(",");
			}
			sb1.deleteCharAt(sb1.length() - 1).append(") ");
		}
		sb1.append("SELECT 1 FROM DUAL");
		sb1.insert(0, "INSERT ALL ");
		System.out.println(sb1.toString());
		try{
			tableDDLMapper.alterTable(sb1.toString());
			summary.setSuccessNum(listnew.size());
			summary.setFailNum(0);
			summary.setStatus(TaskDataRecordStateEnum.taskStateSuccess);
			
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setMessage(ex.getMessage());
			voRes.setFail(voRes);
			summary.setSuccessNum(0);
			summary.setFailNum(listnew.size());
			summary.setStatus(TaskDataRecordStateEnum.taskStateFail);
		}
		taskDataRecordSummaryMapper.insert(summary);
		return voRes;
	}
	
	@Override
	public VoResponse send(MdmModel model, MdmBs bs, String processId) {
		VoResponse voRes = new VoResponse();
		List<TableDefinition> mdmtables = model.getTableDefinitions();
		//模块下获取自定义表
		TableDefinition mdmtable = mdmtables.get(0);
		List<ServiceInterfaceDefined> siDefineds = bs.getSiDefineds();
		ServiceInterfaceDefined siDefined = siDefineds.get(0);
		List<ServiceInterfaceParam> siParams = siDefined.getSiParams();
		ServiceInterfaceParam siParam = null;
		for(ServiceInterfaceParam entity : siParams){
			if(entity.getIoType().equals(SIParamEnum.paramOut)){
				siParam = entity;
				break;
			}
		}
		//获取映射关系
		List<MdmTableMap> listMap = tableMapMapper.findByTableIdAndType(siParam.getsParamTableDefineds().get(0).getId(),
				mdmtable.getId(), DataMapEnum.mdmSource, DataMapEnum.allSource);
		//获取映射字段
		StringBuffer queryFieldMdm = new StringBuffer();
		StringBuffer queryFieldBs = new StringBuffer();
		for(MdmTableMap tableMap : listMap){
			queryFieldBs.append(tableMap.getSpFieldDefined().getFieldName()).append(",");
			queryFieldMdm.append(tableMap.getMdmFieldId()).append(",");
		}
		queryFieldBs.deleteCharAt(queryFieldBs.length() - 1);
		queryFieldMdm.deleteCharAt(queryFieldMdm.length() - 1);
		System.out.println(queryFieldBs);
		System.out.println(queryFieldMdm);
		//业务系统下表数据
		List<Map<String,Object>> listBs = tableDDLMapper.findTableField(siParam.getTableName(), queryFieldBs.toString(), "1=1");
		//MDM模块下自定义表的数据
		List<Map<String,Object>> listMdm = tableDDLMapper.findTableField(mdmtable.getTableName(), queryFieldMdm.toString(), "1=1");
		
		List<Map<String,Object>> listnew = new ArrayList<Map<String,Object>>();
		boolean flag;
		//遍历，根据映射字段，判断字段类型，
		for(Map<String,Object> mapmdm : listMdm){
			flag = false;
			second:for(Map<String,Object> mapbs : listBs){
				for(MdmTableMap tableMap : listMap){
					if(mapmdm.get(tableMap.getMdmFieldId().toUpperCase()).equals(mapbs.get(tableMap.getSpFieldDefined().getFieldName().toUpperCase())) ){
						//值相等，存在
						flag = false;
						continue second;
					}
					flag = true;
				}
			}
			if(!flag){
				listnew.add(mapmdm);
			}
		}
		
		if(listnew.size() == 0){
			voRes.setMessage("没有同步的数据");
			return voRes;
		}
		
		StringBuffer sb = new StringBuffer();
		//sb.append("INSERT ALL ");
		sb.append("INTO ").append(siParam.getTableName().toLowerCase());
		for(MdmTableMap tableMap : listMap){
			sb.append(tableMap.getMdmFieldId()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(")");
		sb.append("VALUES(");
		StringBuffer sb1 = new StringBuffer();
		for(Map<String,Object> map : listnew){
			sb1.append(sb);
			//System.out.println(map);
			for(MdmTableMap tableMap : listMap){
				//System.out.println(tableMap.getSpFieldDefined().getFieldName());
				//System.out.println(map.get(tableMap.getSpFieldDefined().getFieldName()));
				sb1.append("'").append(map.get(tableMap.getSpFieldDefined().getFieldName().toUpperCase())).append("'").append(",");
			}
			sb1.deleteCharAt(sb1.length() - 1).append(") ");
		}
		sb1.append("SELECT 1 FROM DUAL");
		sb1.insert(0, "INSERT ALL ");
		System.out.println(sb1.toString());
		try{
			tableDDLMapper.alterTable(sb1.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setMessage(ex.getMessage());
			voRes.setFail(voRes);
		}
		return voRes;
	}

	@Override
	public void setMdmTableMap(TableDefinition table) {
		tableMapMapper.findByMdmTableId(table.getId());
	}

	@Override
	public VoResponse getMdmTableMapById(MdmModel model, String bsId) {
		VoResponse voRes = new VoResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		List<TableDefinition>  listTable = model.getTableDefinitions();
		//TableDefinition mdmTableDefined = model.getTableDefinitions().get(0);
		
		ServiceParamTableDefined bsTableDefined = null;
		ServiceInterfaceDefined siDefined = serviceInterfaceDefinedMapper.findByModelIdAndBsId(model.getId(), bsId);
		
		if(siDefined != null && siDefined.getSiParams() != null && siDefined.getSiParams().size() > 0){
			for(ServiceInterfaceParam siParam : siDefined.getSiParams()){
				if(siParam.getIoType().equals(SIParamEnum.paramOut)){
					bsTableDefined = siParam.getsParamTableDefineds().get(0);
				}
			}
			
			for(TableDefinition mdmTableDefined : listTable){
				List<MdmTableMap> list = tableMapMapper.findByTableId(bsTableDefined.getId(), mdmTableDefined.getId());
				if(list != null && list.size() > 0){
					for(MdmTableMap tableMap : list){
						if(tableMap.getBsIoType().equals(DataMapEnum.mdmSource)){
							for(ColumnDefinition field : mdmTableDefined.getColumnDefinitions()){
								if(field.getName().equals(tableMap.getMdmFieldId())){
									field.setTargetId(tableMap.getBsFieldId());
								}
							}
							
						}else if(tableMap.getBsIoType().equals(DataMapEnum.bsSource)){
							for(ServiceParamFieldDefined field : bsTableDefined.getsParamFieldDefineds()){
								if(field.getId().equals(tableMap.getBsFieldId())){
									field.setTargetId(tableMap.getMdmFieldId());
								}
							}
							
						}else if(tableMap.getBsIoType().equals(DataMapEnum.allSource)){
							for(ColumnDefinition field : mdmTableDefined.getColumnDefinitions()){
								if(field.getName().equals(tableMap.getMdmFieldId())){
									field.setTargetId(tableMap.getBsFieldId());
								}
							}
							for(ServiceParamFieldDefined field : bsTableDefined.getsParamFieldDefineds()){
								if(field.getId().equals(tableMap.getBsFieldId())){
									field.setTargetId(tableMap.getMdmFieldId());
								}
							}
						}
					}
				}
			}
			
		}
		map.put("mdmTable", listTable);
		map.put("bsTable", bsTableDefined);
		voRes.setData(map);
		return voRes;
	}

	@Override
	public VoResponse syncToMdm(String modelId, String processId) {
		VoResponse voRes = new VoResponse();
		MdmModel model = mdmModelMapper.findById(modelId);
		if(model == null){
			voRes.setNull(voRes);
			voRes.setMessage("mdm模块获取失败");
			return voRes;
		}
		List<ServiceInterfaceDefined> list = serviceInterfaceDefinedMapper.findByModelId(modelId);
		//List<MdmBs> listBs = model.getMdmBses();
		for(ServiceInterfaceDefined defined : list){
			MdmBs bs = mdmBsMapper.findById(defined.getBsId());
			serviceExecute(model, bs, processId);
		}
		return voRes;
	}

	@Override
	public VoResponse send(String modelId, String processId) {
		VoResponse voRes = new VoResponse();
		MdmModel model = mdmModelMapper.findById(modelId);
		if(model == null){
			voRes.setNull(voRes);
			voRes.setMessage("mdm模块获取失败");
			return voRes;
		}
		
		List<MdmBs> listBs = model.getMdmBses();
		List<MdmTableMap> listMap = null;
		
		for(MdmBs bs : listBs){
			listMap = getById(model.getTableDefinitions().get(0).getId(), bs.getSiDefineds().get(0).getSiParams().get(0).getsParamTableDefineds().get(0).getId());
			voRes.setMessage(voRes.getMessage() + send(model, bs, processId).getMessage());
		}
		return voRes;
	}

	@Override
	public VoResponse syncToMdm(String modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoResponse send(String modelId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取自定义table的有没有连线的数据
	 * @param tableName 自定义表名
	 * @param queryParam 查询条件
	 * @param modelId
	 * @param bsId
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param isSelect 是否连线
	 * @return
	 */
	@Override
	public Map<String, Object> getPage(String tableName, Map<String, String> queryParam,String modelId,String bsId, int page, int pageSize, String order,boolean isSelect, boolean isMain) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder conditions = new StringBuilder();
		List<MdmDataMap> listdata = null;
		if(isSelect){
			listdata = dataMapMapper.findByMdmIdAndBsId(modelId, bsId);
			conditions.append(" AND id IN(select mdm_data_id from MDM_DATA_MAPPER where model_id='");
			conditions.append(modelId).append("'").append(" AND bs_id='").append(bsId).append("')");
		}else{
			conditions.append(" AND id NOT IN(select mdm_data_id from MDM_DATA_MAPPER where model_id='");
			conditions.append(modelId).append("'").append(" AND bs_id='").append(bsId).append("')");
		}
		PageQuery pageQuery = new PageQuery();
		pageQuery.setTableName(tableName);
		int count = tableDDLMapper.findCount(conditions.toString(), queryParam, pageQuery.getTableName());
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(page);
		pageQuery.setPageRowSize(pageSize);
		pageQuery.setOrder("create_time");
		pageQuery.calcutePage(pageQuery);
		List<Map<String,Object>>  list = tableDDLMapper.findByPage(conditions.toString(),queryParam, pageQuery);
		if(listdata != null && listdata.size() > 0){
			if(isMain){
				for(MdmDataMap dataMap : listdata){
					for(Map<String,Object> data : list){
						if(dataMap.getMdmDataId().equals(data.get("ID"))){
							data.put("targetId", dataMap.getBsDataId());
						}
					}
				}
			}
		}
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	@Override
	public Map<String, Object> getPage(String tableName, Map<String, String> queryParam, int page, int pageSize, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageQuery pageQuery = new PageQuery();
		pageQuery.setTableName(tableName);
		int count = tableDDLMapper.findCount(null, queryParam, pageQuery.getTableName());
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(page);
		pageQuery.setPageRowSize(pageSize);
		pageQuery.setOrder("create_time");
		pageQuery.calcutePage(pageQuery);
		List<Map<String,Object>>  list = tableDDLMapper.findByPage(null, queryParam, pageQuery);
		map.put("total", count);
		map.put("rows", list);
		return map;
	}

	@Override
	public List<MdmDataMap> getDataMapById(String modelId,String bsId) {
		List<MdmDataMap> list = dataMapMapper.findByMdmIdAndBsId(modelId, bsId);
		return list;
	}

	@Override
	public VoResponse saveAll(List<MdmDataMap> list) {
		VoResponse voRes = new VoResponse();
		String username = getUserName();
		//dataMapMapper.delByModelIdAndBsId(modelId, bsId);
		MdmDataMap e = null;
		for(MdmDataMap entity : list){
			e = dataMapMapper.findByMdmId(entity);
			if(e != null){
				java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
				e.setBsDataId(entity.getBsDataId());
				e.setModifier(username);
				e.setModifierTime(date);
				dataMapMapper.update(e);
			}else{
				entity.setCreater(username);
				dataMapMapper.insert(entity);
			}
		}
		return voRes;
	}

	@Override
	public VoResponse delAll(List<MdmDataMap> list) {
		VoResponse voRes = new VoResponse();
		MdmDataMap e = null;
		for(MdmDataMap entity : list){
			e = dataMapMapper.findByMdmId(entity);
			if(e != null){
				dataMapMapper.del(e.getId());
			}
		}
		return voRes;
	}

}
