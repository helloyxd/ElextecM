package com.elextec.mdm.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.ResponseCodeEnum;
import com.elextec.mdm.common.entity.TableDDLMap;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.mapper.TableDefinitionMapper;
import com.elextec.mdm.service.ITableDDLService;

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
			for(Integer key : obj.getDataTypeMap().keySet()){
				String dataType = TableDDLMap.oracleDataTypeMap.get(key);
				switch(dataType){
					case "CHAR":
					case "VARCHAR2":
					case "NUMBER":
					case "DECIMAL":
						sb.append(dataType).append("(").append(obj.getDataTypeMap().get(key)).append(")");
						break;
					default:
						sb.append(dataType).append(obj.getDataTypeMap().get(key));
						break;
				}
			}
			List<Integer> constraints = obj.getConstraints();
			if(constraints != null){
				for(Integer i : constraints){
					sb.append(" ").append(TableDDLMap.oracleColumnConstraintMap.get(i));
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
		
		List<Map<String, String>> list = tableDDLMapper.getTableColumnDefine(table.getTableName().toUpperCase());
		for(Map<String, String> map : list){
			System.out.println(map);
			ColumnDefinition entity = new ColumnDefinition();
			entity.setName(map.get("column_name"));
			
			table.getColumnDefinitions().add(entity);
			
		}
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
