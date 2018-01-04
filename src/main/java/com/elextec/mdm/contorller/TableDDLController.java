package com.elextec.mdm.contorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.TableDDLMap;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.entity.TableRelation;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.utils.StringUtil;

@RestController
@RequestMapping("/mdm/table")
public class TableDDLController {

	@Autowired
	private ITableDDLService tableDDLService;
	
	@GetMapping("getAll")
	public Object getAll(){
		VoResponse voRes = new VoResponse();
		voRes.setData(tableDDLService.getAll());
		return voRes;
	}
	
	@GetMapping
	public Object getById(@RequestParam("id") String id){
		return tableDDLService.getTableDefinition(id);
	}
	
	@PostMapping
	public Object create(@RequestBody TableDefinition table){
		VoResponse voRes = new VoResponse();
		if(table.getTableName() == null || table.getTableName().equals("")){
			voRes.setNull(voRes);
			voRes.setMessage("tableName is null");
			return voRes;
		}else if(!StringUtil.validateTableName(table.getTableName())){
			voRes.setFail(voRes);
			voRes.setMessage("表名只允许字母开头，允许30字节，允许字母数字下划线");
			return voRes;
		}else if(StringUtil.validateTableNameKeyWord(table.getTableName())){
			voRes.setFail(voRes);
			voRes.setMessage("表名不能为数据库关键字");
			return voRes;
		}
		List<ColumnDefinition> list = table.getColumnDefinitions();
		for(ColumnDefinition obj : list){
			if(obj.getName() == null || obj.getName().equals("")){
				voRes.setNull(voRes);
				voRes.setMessage("columnName is null");
				return voRes;
			}else if(!StringUtil.validateTableName(obj.getName())){
				voRes.setFail(voRes);
				voRes.setMessage("列名只允许字母开头，允许30字节，允许字母数字下划线");
				return voRes;
			}else if(StringUtil.validateTableNameKeyWord(obj.getName())){
				voRes.setFail(voRes);
				voRes.setMessage("列名"+obj.getName()+"为数据库关键字");
				return voRes;
			}
			for(String key : obj.getDataTypeMap().keySet()){
				if(TableDDLMap.oracleDataTypeMap.get(key) == null){
					voRes.setNull(voRes);
					voRes.setMessage(obj.getName() + " dataType is null");
					return voRes;
				}
			}
		}
		voRes = tableDDLService.createTable(table);
		return voRes;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id){
		VoResponse voRes = tableDDLService.dropTable(id);
		return voRes;
	}
	
	@GetMapping("getBasicData")
	public Object getDDLBasicData(){
		VoResponse voRes = new VoResponse();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("DataTypeMap", TableDDLMap.oracleDataTypeMap);
		map.put("ColumnConstraintMap", TableDDLMap.oracleColumnConstraintMap);
		voRes.setData(map);
		return voRes;
	}
	
	@PostMapping("addTableRelation")
	public Object addTableRelation(@RequestBody TableRelation tableRelation){
		VoResponse voRes = tableDDLService.addTableRelation(tableRelation);
		return voRes;
	}
	
	@GetMapping("getTableRelations")
	public Object getTableRelations(){
		VoResponse voRes = new VoResponse();
		voRes.setData(tableDDLService.getTableRelations());
		return voRes;
	}
	
	@GetMapping("defined/{modelId}/{tableId}")
	public Object getDefinedData(@PathVariable("modelId") String modelId, @PathVariable("tableId") String tableId){
		return tableDDLService.getDefinedData(modelId, tableId);
	}
	
	@PostMapping("defined/{modelId}/{tableId}")
	public Object postDefinedData(@PathParam("modelId") String modelId, @PathParam("tableId") String tableId ){
		return tableDDLService.postDefinedData(modelId, tableId);
	}
	
	@DeleteMapping("defined/{modelId}/{tableId}")
	public Object delDefinedData(@PathParam("modelId") String modelId, @PathParam("tableId") String tableId){
		return tableDDLService.delDefinedData(modelId, tableId);
	}
	
	@PutMapping("defined/{modelId}/{tableId}")
	public Object updateDefinedData(@PathParam("modelId") String modelId, @PathParam("tableId") String tableId){
		return tableDDLService.updateDefinedData(modelId, tableId);
	}
}
