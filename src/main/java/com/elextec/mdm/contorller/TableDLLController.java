package com.elextec.mdm.contorller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.ResponseCodeEnum;
import com.elextec.mdm.common.entity.TableDDLMap;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.service.ITableDLLService;
import com.elextec.mdm.utils.TableDDLUtil;

@RestController
@RequestMapping("table")
public class TableDLLController {

	@Autowired
	private ITableDLLService tableDLLService;
	
	@PostMapping
	public Object create(@RequestBody TableDefinition table){
		VoResponse voRes = new VoResponse();
		if(table.getTableName() == null || table.getTableName().equals("")){
			voRes.setNull(voRes);
			voRes.setMessage("tableName is null");
			return voRes;
		}
		List<ColumnDefinition> list = table.getColumnDefinitions();
		for(ColumnDefinition obj : list){
			if(obj.getName() == null || obj.getName().equals("")){
				voRes.setNull(voRes);
				voRes.setMessage("columnName is null");
				return voRes;
			}
			for(Integer key : obj.getDataTypeMap().keySet()){
				if(TableDDLMap.oracleDataTypeMap.get(key) == null){
					voRes.setNull(voRes);
					voRes.setMessage("dataType is null");
					return voRes;
				}
			}
		}
		voRes = tableDLLService.createTable(table);
		return voRes;
	}
}
