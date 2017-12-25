package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.ResponseCodeEnum;
import com.elextec.mdm.common.entity.constant.TableDDLMap;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.mapper.TableDefinitionMapper;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.utils.StringUtil;

@Service
public class TableDDLService implements ITableDDLService {
	
	@Autowired
	private TableDDLMapper tableDDLMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	@Autowired
	private TableDefinitionMapper tableDefinitionMapper;
	
	@Override
	public VoResponse createTable(TableDefinition table) {
		VoResponse voRes = new VoResponse();
		MdmModel mdmModel = mdmModelMapper.findById(table.getModelId());
		if(mdmModel == null){
			voRes.setNull(voRes);
			voRes.setMessage("MDM Model is null");
			return voRes;
		}
		if(tableDDLMapper.queryTableName(table.getTableName().toUpperCase()) > 0){
			voRes.setFail(voRes);
			voRes.setMessage("tableName " + table.getTableName() + " is alreadly exist");
			return voRes;
		}
		
		table.setModelId(mdmModel.getId());
		StringBuilder sb = new StringBuilder();
		StringBuilder sbComment  = new StringBuilder();
		sbComment.append("BEGIN ");
		sb.append("CREATE TABLE ").append(table.getTableName()).append("(");//.append("(\n");
		List<ColumnDefinition> list = table.getColumnDefinitions();
		Iterator<ColumnDefinition> iter = list.iterator();
		while(iter.hasNext()){
			ColumnDefinition obj = iter.next();
			sb.append(obj.getName()).append(" ");
			sbComment.append("EXECUTE IMMEDIATE 'COMMENT ON COLUMN ").append(table.getTableName()).append(".").append(obj.getName());
			sbComment.append(" IS ''").append(obj.getColumnComment()).append("''';");
			for(String key : obj.getDataTypeMap().keySet()){
				String dataType = TableDDLMap.oracleDataTypeMap.get(key);
				switch(dataType){
					case "CHAR":
					case "VARCHAR2":
					case "DECIMAL":
						sb.append(dataType).append("(").append(obj.getDataTypeMap().get(key)).append(")");
						break;
					case "NUMBER(P,S)":
						sb.append("NUMBER").append("(").append(obj.getDataTypeMap().get(key)).append(")");
						break;
					default:
						sb.append(dataType);
						break;
				}
			}
			List<String> constraints = obj.getConstraints();
			if(constraints != null && constraints.size() > 0){
				for(String s : constraints){
					switch(s){
						case "N":
						case "P":
							sb.append(" ").append(TableDDLMap.oracleColumnConstraintMap.get(s));
							break;
						default:
							sb.append(" ").append(s);
							break;	
					}
						
				}
			}
			if(iter.hasNext()){
				sb.append(",");
			}
			//sb.append("\n");
		}
		sb.append(")");
		sbComment.append(" END;");
		System.out.println(sb.toString());
		System.out.println(sbComment.toString());
		String createSql = sb.toString();
		try{
			tableDDLMapper.createTable(createSql);
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setCode(ResponseCodeEnum.CodeFail);
			voRes.setSuccess(false);
			voRes.setMessage(ex.getMessage());
			return voRes;
		}
		voRes.setMessage("create table success");
		tableDDLMapper.createTable(sbComment.toString());
		tableDefinitionMapper.insert(table);
		return voRes;
	}
	
	@Override
	public VoResponse dropTable(String id) {
		VoResponse voRes = new VoResponse();
		TableDefinition table = tableDefinitionMapper.findById(id);
		if(table == null){
			voRes.setNull(voRes);
			voRes.setMessage("TableDefinition is null");
			return voRes;
		}
		String tableName = table.getTableName();
		int count = tableDDLMapper.queryTable(tableName);
		if(count > 0){
			voRes.setFail(voRes);
			voRes.setMessage("tableName " + tableName + " alreadly exist " + count + " data");
			return voRes;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE ").append(tableName);
		System.out.println(sb.toString());
		tableDDLMapper.dropTable(sb.toString());
		tableDefinitionMapper.del(id);
		return voRes;
	}
	
	@Override
	public VoResponse getTableDefinition(String id){
		VoResponse voRes = new VoResponse();
		TableDefinition table = tableDefinitionMapper.findById(id);
		if(table == null){
			voRes.setNull(voRes);
			voRes.setMessage("TableDefinition is null");
			return voRes;
		}
		String tableName = table.getTableName().toUpperCase();
		List<Map<String, String>> list = tableDDLMapper.getColumnCommentsDefine(tableName);
		List<Map<String, String>> listColumns = tableDDLMapper.getTableColumnsDefine(tableName);
		String dataType = null;
		String dataLength = null;
		List<ColumnDefinition> listColumnsDefinition = new ArrayList<ColumnDefinition>();
		
		for(Map<String, String> map : list){
			System.out.println(map);
			ColumnDefinition entity = new ColumnDefinition();
			entity.setName(map.get("COLUMN_NAME"));
			entity.setColumnComment(map.get("COMMENTS"));
			if(map.get("COLUMN_NAME").equals("ID")){
				List<String> constraints = new ArrayList<String>();
				constraints.add("P");
				entity.setConstraints(constraints);
				Map<String, String> dataTypeMap = new HashMap<String, String>();
				dataTypeMap.put("VARCHAR2", "32");
				entity.setDataTypeMap(dataTypeMap);
				listColumnsDefinition.add(entity);
				continue;
			}
			for(Map<String, String> map1 : listColumns){
				if(entity.getName().equals(map1.get("COLUMN_NAME"))){
					System.out.println(map1);
					List<String> constraints = new ArrayList<String>();
					constraints.add(TableDDLMap.oracleColumnConstraintMap.get(map1.get("NULLABLE")));
					entity.setConstraints(constraints);
					Map<String, String> dataTypeMap = new HashMap<String, String>();
					dataType = map1.get("DATA_TYPE");
					dataLength = String.valueOf(map1.get("DATA_LENGTH"));
					switch(dataType){
						case "CHAR":
						case "VARCHAR2":
						case "LONG":
						case "BLOB":
						case "FLOAT":
						case "REAL":
							dataTypeMap.put(dataType, dataLength);
							break;
						case "NUMBER":
							if(map1.get("DATA_PRECISION") == null){
								dataTypeMap.put("INTEGER", "");
							}else{
								dataTypeMap.put(dataType, String.valueOf(map1.get("DATA_PRECISION")) + 
										"," + String.valueOf(map1.get("DATA_SCALE")));
							}
							break;
						case "DATE":
						case "TIMESTAMP(6)":
							dataTypeMap.put(TableDDLMap.oracleDataTypeMap.get(dataType), "");
							break;
						default:
							dataTypeMap.put(dataType, dataLength);
							break;
					}
					entity.setDataTypeMap(dataTypeMap);
				}
			}
			listColumnsDefinition.add(entity);
		}
		table.setColumnDefinitions(listColumnsDefinition);
		voRes.setData(table);
		return voRes;
	}
	
	public VoResponse alterTable(TableDefinition table) {
		String alterTable = "";
		tableDDLMapper.alterTable(alterTable);
		return null;
	}
	
	@Override
	public List<TableDefinition> getAll() {
		List<TableDefinition> list = tableDefinitionMapper.findAll();
		return list;
	}

	@Override
	public TableDefinition getById(String id){
		TableDefinition table = tableDefinitionMapper.findById(id);
		return table;
	}
}
