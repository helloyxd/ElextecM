package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.DataMapEnum;
import com.elextec.mdm.common.entity.constant.SIParamEnum;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.MdmTableMap;
import com.elextec.mdm.entity.ServiceInterfaceDefined;
import com.elextec.mdm.entity.ServiceInterfaceParam;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmDataMapMapper;
import com.elextec.mdm.mapper.MdmTableMapMapper;
import com.elextec.mdm.mapper.ServiceInterfaceDefinedMapper;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IDataMapService;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.vo.VoDataMap;

@Service
public class DataMapService extends BaseService implements IDataMapService{

	@Autowired
	private MdmDataMapMapper dataMapMapper;
	
	@Autowired
	private MdmTableMapMapper tableMapMapper;
	
	@Autowired
	private TableDDLMapper tableDDLMapper;
	
	@Override
	public void save(MdmTableMap tableMap) {
		tableMap.setCreater(getUserName());
		tableMapMapper.insert(tableMap);
	}

	@Override
	public VoResponse del(String id) {
		// TODO Auto-generated method stub
		return null;
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
	public VoResponse syncToMdm(MdmModel model, MdmBs bs, List<MdmTableMap> list) {
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
		
		if(listnew.size() == 0){
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

}
