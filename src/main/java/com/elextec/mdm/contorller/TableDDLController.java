package com.elextec.mdm.contorller;

import java.util.ArrayList;
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
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.QueryFieldDefined;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.entity.TableRelation;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.IMenuService;
import com.elextec.mdm.service.IQueryFieldDefinedService;
import com.elextec.mdm.service.ITableDDLService;
import com.elextec.mdm.utils.StringUtil;

@RestController
@RequestMapping("/mdm/table")
public class TableDDLController {

	@Autowired
	private ITableDDLService tableDDLService;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IMdmModelService mdmModelService;
	
	@Autowired
	private IQueryFieldDefinedService queryFieldDefinedService;
	
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
			voRes.setMessage("表名不能为空");
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
				voRes.setMessage("列名不能为空");
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
			//判断dataTypeMap
			for(String key : obj.getDataTypeMap().keySet()){
				if(TableDDLMap.oracleDataTypeMap.get(key) == null){
					voRes.setNull(voRes);
					voRes.setMessage(obj.getName() + " 数据类型异常");
					return voRes;
				}
			}
			//判断constraints
		}
		voRes = tableDDLService.createTable(table);
		if(voRes.getSuccess()){
			if(table.getIsMenu()){
				System.out.println(table.getTableName());
				System.out.println(table.getTableLabel());
				
				if(!menuService.createMDMenu((MdmModel) voRes.getData(), table.getTableName(), table.getTableLabel())){
					voRes.setMessage(voRes.getMessage() + "<br>创建菜单失败");
				}
			}
		}
		return voRes;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id){
		VoResponse voRes = tableDDLService.dropTable(id);
		if(voRes.getSuccess()){
			if(!menuService.dropMDMenu((TableDefinition) voRes.getData())){
				voRes.setMessage(voRes.getMessage() + "<br>删除菜单失败");
			}
		}
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
	
	@GetMapping("defined/{menuId}")
	public Object getDefinedData(@PathVariable("menuId") String menuId){
		VoResponse voRes = new VoResponse();
		Menu menu = menuService.getById(menuId);
		MdmModel model = mdmModelService.getByName(menu.getMenuName());
		TableDefinition table = model.getTableDefinitions().get(0);
		tableDDLService.setColumnsDefinition(table);
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapColumn = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(ColumnDefinition column : table.getColumnDefinitions()){
			if(column.getConstraints().contains("P")){
				continue;
			}
			mapColumn = new HashMap<String,Object>();
			mapColumn.put("ch", column.getColumnComment());
			mapColumn.put("en", column.getName());
			list.add(mapColumn);
		}
		map.put("definedColumn", list);
		map.put("definedData", tableDDLService.getDefinedData(model).getData());
		voRes.setData(map);
		return voRes;
	}
	
	@PostMapping("defined/{modelId}")
	public Object postDefinedData(@PathParam("modelId") String modelId, @PathParam("tableId") String tableId ){
		return tableDDLService.postDefinedData(modelId);
	}
	
	@DeleteMapping("defined/{modelId}")
	public Object delDefinedData(@PathParam("modelId") String modelId, @PathParam("tableId") String tableId){
		return tableDDLService.delDefinedData(modelId);
	}
	
	@PutMapping("defined/{modelId}")
	public Object updateDefinedData(@PathParam("modelId") String modelId, @PathParam("tableId") String tableId){
		return tableDDLService.updateDefinedData(modelId);
	}
	
	@PostMapping("queryFieldDefined")
	public Object addQueryFieldDefined(@RequestBody QueryFieldDefined queryFieldDefined){
		VoResponse voRes = queryFieldDefinedService.add(queryFieldDefined);
		return voRes;
	}
	
	@DeleteMapping("queryFieldDefined")
	public Object delQueryFieldDefined(@RequestParam("id") String id){
		VoResponse voRes = queryFieldDefinedService.del(id);
		return voRes;
	}
	
	@PutMapping("queryFieldDefined")
	public Object updateQueryFieldDefined(@RequestBody QueryFieldDefined queryFieldDefined){
		VoResponse voRes = queryFieldDefinedService.add(queryFieldDefined);
		return voRes;
	}
	
	@GetMapping("queryFieldDefined")
	public Object getQueryFieldDefined(@RequestParam("tableId") String tableId){
		VoResponse voRes = queryFieldDefinedService.getById(tableId);
		return voRes;
	}
}
