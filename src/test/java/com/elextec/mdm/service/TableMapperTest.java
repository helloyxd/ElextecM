package com.elextec.mdm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.mapper.TableDLLMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TableMapperTest {

	@Autowired
	private TableDLLMapper tableDLLMapper;
	
	@Test
	public void createTable() throws Exception {
		String sql = "";
		tableDLLMapper.createTable(sql);
	}
}
