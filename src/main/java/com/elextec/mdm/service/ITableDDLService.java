package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.TableDefinition;

public interface ITableDDLService {
	
	public VoResponse createTable(TableDefinition table);
	
	public VoResponse dropTable(String id);
	
	public VoResponse alterTable(TableDefinition table);
	
	public List<Map> getDBDataType();
	
	public List<TableDefinition> getAll();
	
}
