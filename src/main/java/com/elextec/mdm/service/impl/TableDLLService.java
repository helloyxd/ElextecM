package com.elextec.mdm.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.common.entity.ResponseCodeEnum;
import com.elextec.mdm.common.entity.TableDDLMap;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.TableDLLMapper;
import com.elextec.mdm.service.ITableDLLService;

public class TableDLLService implements ITableDLLService {
	
	@Autowired
	private TableDLLMapper tableDLLMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	public VoResponse createTable(TableDefinition table) {
		VoResponse voRes = new VoResponse();
		MdmModel mdmModel = mdmModelMapper.findById(table.getModelId());
		if(mdmModel == null){
			voRes.setNull(voRes);
			voRes.setMessage("MDM Model is null");
			return voRes;
		}
		mdmModel.getMdmModel();
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(table.getTableName()).append("(\n");
		List<ColumnDefinition> list = table.getColumnDefinitions();
		Iterator<ColumnDefinition> iter = list.iterator();
		while(iter.hasNext()){
			ColumnDefinition obj = iter.next();
			sb.append(obj.getName()).append(" ");
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
			sb.append(" ").append(obj.getConstraint());
			if(iter.hasNext()){
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append(");");
		System.out.println(sb.toString());
		String createSql = sb.toString();
		try{
			tableDLLMapper.createTable(createSql);
		}catch(Exception ex){
			voRes.setCode(ResponseCodeEnum.CodeFail);
			voRes.setSuccess(false);
			voRes.setMessage(ex.getMessage());
		}
		voRes.setMessage("create table success");
		return voRes;
	}
	
	
	
	
	public void dropTable(String tableName) {
		String dropTable = "";
		tableDLLMapper.dropTable(dropTable);
	}
	
	public void alterTable(TableDefinition table) {
		String alterTable = "";
		tableDLLMapper.alterTable(alterTable);
	}
	
	public List<Map> getDBDataType(){
		return tableDLLMapper.getDBDataType();
	}
	
}
