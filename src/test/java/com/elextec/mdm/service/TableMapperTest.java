package com.elextec.mdm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.entity.ColumnDefinition;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.TableDDLMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableMapperTest {

	@Autowired
	private TableDDLMapper tableDLLMapper;
	
	@Autowired
	private ITableDDLService tableDLLService;
	
	@Test
	public void createTable() throws Exception {
		String sql = "";
		tableDLLMapper.createTable(sql);
	}
	
	@Test
	public void createTableService() throws Exception {
		TableDefinition table = new TableDefinition();
		table.setModelId("");
		table.setTableName("MDMTest");
		List<ColumnDefinition> list = new ArrayList<ColumnDefinition>();
		ColumnDefinition obj = new ColumnDefinition();
		obj.setName("id");
		Map<String,String> dataTypeMap = new HashMap<String,String>();
		dataTypeMap.put("", "");
		obj.setDataTypeMap(dataTypeMap);
		List<String> constraints = new ArrayList<String>();
		constraints.add("");
		constraints.add("");
		obj.setConstraints(constraints);
		list.add(obj);
		table.setColumnDefinitions(list);
		tableDLLService.createTable(table);
		
	}
	
}
