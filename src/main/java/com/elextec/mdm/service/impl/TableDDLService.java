package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.ResponseCodeEnum;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.common.entity.constant.TableDDLMap;
import com.elextec.mdm.common.entity.constant.TableRelationEnum;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.DataPermission;
import com.elextec.mdm.entity.DataPermissionDefined;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.entity.TableRelation;
import com.elextec.mdm.mapper.DataPermissionDefinedMapper;
import com.elextec.mdm.mapper.DataPermissionMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.TableDDLMapper;
import com.elextec.mdm.mapper.TableDefinitionMapper;
import com.elextec.mdm.mapper.TableRelationMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.utils.StringUtil;

@Service
public class TableDDLService extends BaseService implements ITableDDLService {
	
	@Autowired
	private TableDDLMapper tableDDLMapper;
	
	@Autowired
	private TableDefinitionMapper tableDefinitionMapper;
	
	@Autowired
	private TableRelationMapper tableRelationMapper;
	
	@Autowired
	private DataPermissionDefinedMapper dataPermissionDefinedMapper;
	
	@Autowired
	private DataPermissionMapper dataPermissionMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	
	@Override
	@Transactional
	public VoResponse createTable(TableDefinition table) {
		VoResponse voRes = new VoResponse();
		//MdmModel mdmModel = mdmModelMapper.findByIdOnly(table.getModelId());
		
		if(tableDDLMapper.queryTableName(table.getTableName().toUpperCase()) > 0){
			voRes.setFail(voRes);
			voRes.setMessage("表名 " + table.getTableName() + "已经存在");
			return voRes;
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder sbComment  = new StringBuilder();
		sbComment.append("BEGIN ");
		sb.append("CREATE TABLE ").append(table.getTableName()).append("(");//.append("(\n");
		List<ColumnDefinition> list = table.getColumnDefinitions();
		Iterator<ColumnDefinition> iter = list.iterator();
		sb.append("id varchar2(32) primary key,");
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
						case "DAFAULT":
						case "UNIQUE":
						case "Y":
						case "N":
						case "F":
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
		try{
			tableDDLMapper.createTable(sbComment.toString());
			table.setCreater(getUserName());
			if(table.getIsMenu() == null){
				table.setIsMenu(false);
			}
			tableDefinitionMapper.insert(table);
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setCode(ResponseCodeEnum.CodeFail);
			voRes.setSuccess(false);
			voRes.setMessage(ex.getMessage());
			StringBuilder sbDrop = new StringBuilder();
			sbDrop.append("DROP TABLE ").append(table.getTableName());
			System.out.println(sbDrop.toString());
			tableDDLMapper.dropTable(sbDrop.toString());
			return voRes;
		}
		//List<TableDefinition> tableDefinitions = new ArrayList<TableDefinition>();
		//tableDefinitions.add(table);
		//mdmModel.setTableDefinitions(tableDefinitions);
		voRes.setData(table);
		return voRes;
	}
	
	@Override
	@Transactional
	public VoResponse updateTable(TableDefinition table) {
		VoResponse voRes = new VoResponse();
		TableDefinition oldtable = tableDefinitionMapper.findById(table.getId());
		StringBuilder sb = null;
		if(table.getTableName()!=null && !table.getTableName().equals("")){
			if( !oldtable.getTableName().equals(table.getTableName())){
				sb = new StringBuilder();
				sb.append("ALTER TABLE ").append(oldtable.getTableName()).append(" RENAME TO").append(table.getTableName());
				oldtable.setTableName(table.getTableName());
			}
		} 
		if(table.getTableLabel() != null && !table.getTableLabel().equals("")){
			if( !oldtable.getTableLabel().equals(table.getTableLabel())){
				oldtable.setTableLabel(table.getTableLabel());
			}
		}
		if(table.getModelId()!=null && !table.getModelId().equals("")){
			if( !oldtable.getModelId().equals(table.getModelId())){
				MdmModel model = mdmModelMapper.findById(table.getModelId());
				if(model == null){
					voRes.setNull(voRes);
					voRes.setMessage("更新模块错误");
					return voRes;
				}
				if(model.getTableDefinitions()!=null && model.getTableDefinitions().size() > 0){
					voRes.setFail(voRes);
					voRes.setMessage(model.getMdmModel()+"已经存在自定义表");
					return voRes;
				}
				oldtable.setModelId(table.getModelId());
			}
		}
		try{
			tableDDLMapper.alterTable(sb.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setFail(voRes);
			voRes.setMessage(ex.getMessage());
			return voRes;
		}
		tableDefinitionMapper.update(oldtable);
		List<ColumnDefinition> list = table.getColumnDefinitions();
		if(list != null && list.size() > 0){
			String tableName = oldtable.getTableName().toUpperCase();
			String columnName = null;
			List<Map<String, String>>  comments = tableDDLMapper.getColumnCommentsDefine(tableName);
			List<Map<String, String>> columns = tableDDLMapper.getTableColumnsDefine(tableName);
			StringBuilder sbUpdate = null;
			StringBuilder sbAdd = null;
			StringBuilder sbComment  = new StringBuilder();
			sbComment.append("BEGIN ");
			boolean flag = false;
			boolean flagComment = false;
			for(ColumnDefinition column : list){
				columnName = column.getName().toUpperCase();
				
				for(Map<String, String> map : columns){
					if(map.get("COLUMN_NAME").equals(columnName)){
						flag = true;
						if(sbUpdate == null){
							sbUpdate = new StringBuilder();
							sbUpdate.append("ALTER TABLE ").append(tableName).append(" MODIFY (");
						}
						setAlterColumn(sbUpdate, column);
					}
				}
				
				for(Map<String, String> map : comments){
					if(map.get("COLUMN_NAME").equals(columnName)){
						if(column.getColumnComment() != null){
							if(!column.getColumnComment().equals(map.get("COMMENTS"))){
								sbComment.append("EXECUTE IMMEDIATE 'COMMENT ON COLUMN ").append(tableName).append(".").append(columnName);
								sbComment.append(" IS ''").append(column.getColumnComment()).append("''';");
								flagComment = true;
							}
						}
					}
				}
				
				if(!flag){//add
					if(sbAdd == null){
						sbAdd = new StringBuilder();
						sbAdd.append("ALTER TABLE ").append(tableName).append(" ADD (");
					}
					setAlterColumn(sbAdd, column);
					sbComment.append("EXECUTE IMMEDIATE 'COMMENT ON COLUMN ").append(tableName).append(".").append(columnName);
					sbComment.append(" IS ''").append(column.getColumnComment()).append("''';");
					flagComment = true;
					flag = false;
				}
			}
			try{
				if(sbUpdate != null){
					sbUpdate.deleteCharAt(sbUpdate.length() - 1);
					sbUpdate.append(")");
					tableDDLMapper.alterTable(sbUpdate.toString());
				}
			}catch(Exception ex){
				ex.printStackTrace();
				voRes.setFail(voRes);
				voRes.setMessage(ex.getMessage());
				return voRes;
			}
			try{
				if(sbAdd != null){
					sbAdd.append(")");
					sbAdd.deleteCharAt(sbAdd.length() - 1);
					tableDDLMapper.alterTable(sbAdd.toString());
				}
			}catch(Exception ex){
				ex.printStackTrace();
				voRes.setFail(voRes);
				voRes.setMessage(ex.getMessage());
				return voRes;
			}
			try{
				if(flagComment){
					sbComment.append(" END;");
					tableDDLMapper.alterTable(sbComment.toString());
				}
			}catch(Exception ex){
				ex.printStackTrace();
				voRes.setFail(voRes);
				voRes.setMessage(ex.getMessage());
				return voRes;
			}
		}
		return voRes;
	}
	
	private void setAlterColumn(StringBuilder sb,ColumnDefinition column){
		switch(column.getDataType()){
			case "CHAR":
			case "VARCHAR2":
			case "DECIMAL":
			case "NUMBER":
				sb.append(column.getName()).append(" ").append(column.getDataType()).append("(").append(column.getDataTypeLength()).append(") ");
				break;
			default:
				sb.append(column.getName()).append(" ").append(column.getDataType()).append(" ");
				break;
		}
		for(String s : column.getConstraints()){
			if(s.equals("N") || s.equals("Y")){
				sb.append(TableDDLMap.oracleColumnConstraintMap.get(s));
			}
		}
		sb.append(",");
	}
	
	@Override
	public VoResponse dropTable(TableDefinition table) {
		VoResponse voRes = new VoResponse();
		String tableName = table.getTableName();
		int count = 0;
		count = tableDDLMapper.queryTableName(tableName.toUpperCase());
		if(count == 0){//数据表不存在情况
			tableDefinitionMapper.del(table.getId());
			return voRes;
		}
		count = tableDDLMapper.queryTable(tableName);
		if(count > 0){
			voRes.setFail(voRes);
			voRes.setMessage("mdm数据表 " + tableName + " 已经存在 " + count + " 条数据");
			return voRes;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE ").append(tableName);
		System.out.println(sb.toString());
		tableDefinitionMapper.del(table.getId());
		tableDDLMapper.dropTable(sb.toString());
		//table.setModel(mdmModelMapper.findById(table.getModelId()));
		//voRes.setData(table);
		voRes.setMessage("删除定义表"+table.getTableName()+"成功");
		return voRes;
	}
	
	public void setColumnsDefinition(TableDefinition table){
		String tableName = table.getTableName().toUpperCase();
		List<Map<String, String>> list = tableDDLMapper.getColumnCommentsDefine(tableName);
		List<Map<String, String>> listColumns = tableDDLMapper.getTableColumnsDefine(tableName);
		String dataType = null;
		String dataLength = null;
		List<ColumnDefinition> listColumnsDefinition = new ArrayList<ColumnDefinition>();
		List<String> constraints = null;
		for(Map<String, String> map : list){
			System.out.println(map);
			ColumnDefinition entity = new ColumnDefinition();
			entity.setName(map.get("COLUMN_NAME"));
			entity.setColumnComment(map.get("COMMENTS"));
			if(map.get("COLUMN_NAME").equals("ID")){
				constraints = new ArrayList<String>();
				constraints.add("P");
				entity.setConstraints(constraints);
				Map<String, String> dataTypeMap = new HashMap<String, String>();
				dataTypeMap.put("VARCHAR2", "32");
				entity.setDataType("VARCHAR2");
				entity.setDataTypeLength("32");
				entity.setDataTypeMap(dataTypeMap);
				listColumnsDefinition.add(entity);
				continue;
			}
			for(Map<String, String> map1 : listColumns){
				if(entity.getName().equals(map1.get("COLUMN_NAME"))){
					constraints = new ArrayList<String>();
					constraints.add(map1.get("NULLABLE"));
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
							entity.setDataType(dataType);
							entity.setDataTypeLength(dataLength);
							break;
						case "NUMBER":
							if(map1.get("DATA_PRECISION") == null){
								dataTypeMap.put("INTEGER", "");
								entity.setDataType("INTEGER");
								entity.setDataTypeLength("");
							}else{
								dataLength = String.valueOf(map1.get("DATA_PRECISION")) + 
										"," + String.valueOf(map1.get("DATA_SCALE"));
								dataTypeMap.put(dataType, dataLength);
								entity.setDataType(dataType);
								entity.setDataTypeLength(dataLength);
							}
							break;
						case "DATE":
						case "TIMESTAMP(6)":
							dataTypeMap.put(dataType, "");
							entity.setDataType(dataType);
							entity.setDataTypeLength("");
							break;
						default:
							dataTypeMap.put(dataType, dataLength);
							entity.setDataType(dataType);
							entity.setDataTypeLength(dataLength);
							break;
					}
					entity.setDataTypeMap(dataTypeMap);
				}
			}
			listColumnsDefinition.add(entity);
		}
		table.setColumnDefinitions(listColumnsDefinition);
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
		setColumnsDefinition(table);
		List<TableDefinition> tables = new ArrayList<TableDefinition>(); 
		tables.add(table);
		//获取table关系
		List<TableRelation> relations = tableRelationMapper.findByTableId(table.getId());
		if(relations.size() > 0){
			TableDefinition table2 = null;
			for(TableRelation relation : relations){
				table2 = tableDefinitionMapper.findById(relation.getTable2());
				table2.setIsMenu(false);
				setColumnsDefinition(table2);
				tables.add(table2);
			}
			
		}
		//dataPermission
		List<DataPermissionDefined> listDataDefined = dataPermissionDefinedMapper.findByTableId(table.getId());
		List<DataPermission> listData = null;
		Map<String, Object> mapData = new HashMap<String, Object>();
		for(DataPermissionDefined dataDefined : listDataDefined){
			listData = dataPermissionMapper.findDatasByUserIdAndDataDefined(getUserId(), dataDefined.getId());
			mapData.put(dataDefined.getPermissionField(), listData);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", tables);
		map.put("data", mapData);
		voRes.setData(map);
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

	@Override
	public VoResponse addTableRelation(TableRelation tableRelation) {
		VoResponse voRes = new VoResponse();
		TableDefinition table = tableDefinitionMapper.findById(tableRelation.getTable1());
		if(table == null){
			voRes.setNull(voRes);
			voRes.setMessage("table1 is null");
			return voRes;
		}
		TableDefinition table2 = tableDefinitionMapper.findById(tableRelation.getTable2());
		if(table2 == null){
			voRes.setNull(voRes);
			voRes.setMessage("table2 is null");
			return voRes;
		}
		String foreignKey1 = null;
		String foreignKey2 = null;
		if(tableRelation.getRelation() == TableRelationEnum.Relation11){
			foreignKey1 = table2.getTableName() + "_id";
			foreignKey2 = table.getTableName() + "_id";
			tableRelation.setForeignKey1(foreignKey1);
			tableRelation.setForeignKey2(foreignKey2);
		}else if(tableRelation.getRelation() == TableRelationEnum.Relation1N){
			foreignKey2 = table.getTableName() + "_id";
			tableRelation.setForeignKey2(foreignKey2);
		}else if(tableRelation.getRelation() == TableRelationEnum.RelationNN){
			foreignKey1 = table2.getTableName() + "_id";
			foreignKey2 = table.getTableName() + "_id";
			if(StringUtil.validateTableName(tableRelation.getMutiRelationTable())){
				voRes.setFail(voRes);
				voRes.setMessage("表名只允许字母开头，允许30字节，允许字母数字下划线");
				return voRes;
			}else if(StringUtil.validateTableNameKeyWord(tableRelation.getMutiRelationTable())){
				voRes.setFail(voRes);
				voRes.setMessage("表名不能为数据库关键字");
				return voRes;
			}
			if(tableDDLMapper.queryTableName(tableRelation.getMutiRelationTable().toUpperCase()) > 0){
				voRes.setFail(voRes);
				voRes.setMessage("tableName " + table.getTableName() + " is alreadly exist");
				return voRes;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE ").append(tableRelation.getMutiRelationTable()).append("(");
			sb.append("id varchar2(32) primary key,");
			sb.append(foreignKey1).append(" varchar2(32) not null,");
			sb.append(foreignKey2).append(" varchar2(32) not null)");
			System.out.println(sb.toString());
			try{
				tableDDLMapper.createTable(sb.toString());
			}catch(Exception ex){
				ex.printStackTrace();
				voRes.setCode(ResponseCodeEnum.CodeFail);
				voRes.setSuccess(false);
				voRes.setMessage(ex.getMessage());
				return voRes;
			}
		}else{
			voRes.setNull(voRes);
			voRes.setMessage("表关系异常");
			return voRes;
		}
		tableRelation.setStatus(StatusEnum.StatusEnable);
		tableRelation.setCreater(getUserName());
		tableRelationMapper.insert(tableRelation);
		voRes.setData(tableRelation);
		return voRes;
	}

	@Override
	public List<TableRelation> getTableRelations() {
		List<TableRelation> list = tableRelationMapper.findAll();
		return list;
	}

	@Override
	public List<TableDefinition> getByModelId(String modelId) {
		List<TableDefinition> list = tableDefinitionMapper.findByModelId(modelId);
		return list;
	}
	

	@Override
	public VoResponse getDefinedData(MdmModel model) {
		VoResponse voRes = new VoResponse();
		List<TableDefinition> listTable = tableDefinitionMapper.findByModelId(model.getId());
		if(listTable == null || listTable.size() == 0){
			voRes.setNull(voRes);
			return voRes;
		}
		TableDefinition table = listTable.get(0);
		List<TableRelation> listRelation = tableRelationMapper.findByTableId(table.getId());
		List<DataPermissionDefined> listDataPermissionDefined = dataPermissionDefinedMapper.findByTableId(table.getId());
		
		if(listRelation == null || listRelation.size() == 0){
			//List<Object> listObj = tableDDLMapper.findTable(table.getTableName());
			
		}else{
			
		}
		StringBuilder sb = new StringBuilder();
		sb.append("1=1");
		if(listDataPermissionDefined == null || listDataPermissionDefined.size() == 0){
			
		}else{
			
			List<DataPermission> listData = null;
			for(DataPermissionDefined dataDefined : listDataPermissionDefined){
				listData = dataPermissionMapper.findDatasByUserIdAndDataDefined(getUserId(), dataDefined.getId());
				if(listData == null || listData.size() == 0){
					return voRes;
				}
				sb.append(" AND ").append(dataDefined.getPermissionField()).append(" IN (");
				for(DataPermission data : listData){
					sb.append("'").append(data.getPermissionValue()).append("',");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(")");
			}
			
		}
		List<Map<String,Object>> listObj = tableDDLMapper.findTable(table.getTableName(), sb.toString());
		System.out.println(listObj);
		voRes.setData(listObj);
		return voRes;
	}

	@Override
	public VoResponse postDefinedData(TableDefinition table, Map<String,String> map) {
		StringBuilder sb = new StringBuilder("INSERT INTO ");
		StringBuilder sb2 = new StringBuilder("VALUES(sys_guid(),");
		sb.append(table.getTableName()).append("(id,");
		for (String key : map.keySet()) {
			if(map.get(key).equals("")){
				continue;
			}
			sb.append(key).append(",");
			sb2.append("'").append(map.get(key)).append("'").append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb2.deleteCharAt(sb2.length() - 1);
		sb.append(") ");
		sb2.append(")");
		String sql = sb.append(sb2).toString();
		System.out.println(sql);
		VoResponse voRes= new VoResponse();
		try{
			tableDDLMapper.executeTable(sql);
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setFail(voRes);
			voRes.setMessage(ex.getMessage());
		}
		return voRes;
	}

	@Override
	public VoResponse delDefinedData(TableDefinition table, String id) {
		StringBuilder sb = new StringBuilder("DELETE FROM ");
		sb.append(table.getTableName()).append(" WHERE id='").append(id).append("'");
		System.out.println( sb.toString());
		VoResponse voRes= new VoResponse();
		try{
			tableDDLMapper.executeTable(sb.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setFail(voRes);
			voRes.setMessage(ex.getMessage());
		}
		return voRes;
	}

	@Override
	public VoResponse updateDefinedData(TableDefinition table, Map<String,String> map) {
		StringBuilder sb = new StringBuilder("UPDATE ");
		sb.append(table.getTableName()).append(" SET ");
		for (String key : map.keySet()) {
			if(key.equals("ID")){
				continue;
			}
			sb.append(key).append("='").append(map.get(key)).append("',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" WHERE id='").append(map.get("ID")).append("'");
		System.out.println(sb.toString());
		VoResponse voRes= new VoResponse();
		try{
			tableDDLMapper.executeTable(sb.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			voRes.setFail(voRes);
			voRes.setMessage(ex.getMessage());
		}
		return voRes;
	}
}
