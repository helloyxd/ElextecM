package com.elextec.mdm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.elextec.mdm.mapper.TableDLLMapper;
import com.elextec.mdm.service.ITableDLLService;

public class TableDLLService implements ITableDLLService {
	
	@Autowired
	private TableDLLMapper tableDLLMapper;
	
	public void createTable() {
		String createSql = "";
		tableDLLMapper.createTable(createSql);
	}
	
	public void dropTable() {
		String dropTable = "";
		tableDLLMapper.dropTable(dropTable);
	}
	
	public void alterTable() {
		String alterTable = "";
		tableDLLMapper.alterTable(alterTable);
	}
	
	public List<Map> getDBDataType(){
		return tableDLLMapper.getDBDataType();
	}
	
}
