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
import com.elextec.mdm.mapper.MenuMapper;
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
	private MdmModelMapper mdmModelMapper;
	
	@Autowired
	private TableDefinitionMapper tableDefinitionMapper;
	
	@Autowired
	private TableRelationMapper tableRelationMapper;
	
	@Autowired
	private DataPermissionDefinedMapper dataPermissionDefinedMapper;
	
	@Autowired
	private DataPermissionMapper dataPermissionMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	
	@Override
	@Transactional
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
			voRes.setMessage("表名 " + table.getTableName() + "已经存在");
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
		voRes.setData(mdmModel);
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
		table.setModel(mdmModelMapper.findById(table.getModelId()));
		voRes.setData(table);
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
		//dataPermission
		List<DataPermissionDefined> listDataDefined = dataPermissionDefinedMapper.findByTableId(table.getId());
		List<DataPermission> listData = null;
		Map<String, Object> mapData = new HashMap<String, Object>();
		for(DataPermissionDefined dataDefined : listDataDefined){
			listData = dataPermissionMapper.findDatasByUserIdAndDataDefined(getUserId(), dataDefined.getId());
			mapData.put(dataDefined.getPermissionField(), listData);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", table);
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
	public VoResponse postDefinedData(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoResponse delDefinedData(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoResponse updateDefinedData(String modelName) {
		// TODO Auto-generated method stub
		return null;
	}
}
