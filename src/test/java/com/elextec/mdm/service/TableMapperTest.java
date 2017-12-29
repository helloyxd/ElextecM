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
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableMapperTest {

	@Autowired
	private ITableDDLService tableDDLService;
	
	//@Test
	public void createTable() throws Exception {
		String sql = "";
	}
	
	@Test
	public void createTableService() throws Exception {
		TableDefinition table = new TableDefinition();
		table.setModelId("B1536179075A492B96192DE9EDA69CEA");
		table.setTableName("MDMTest9");
		table.setTableLabel("测试");
		table.setCreater("sys");
		table.setStatus(0);
		List<ColumnDefinition> list = new ArrayList<ColumnDefinition>();
		ColumnDefinition obj = new ColumnDefinition();
		obj.setName("id");
		Map<String,String> dataTypeMap = new HashMap<String,String>();
		dataTypeMap.put("VARCHAR2", "32");
		obj.setDataTypeMap(dataTypeMap);
		List<String> constraints = new ArrayList<String>();
		constraints.add("P");
		obj.setConstraints(constraints);
		list.add(obj);
		table.setColumnDefinitions(list);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(tableDDLService.createTable(table)));
	}
	
	
	@Test
	public void getALL() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(tableDDLService.getAll()));
	}
}
