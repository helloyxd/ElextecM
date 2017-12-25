package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.entity.TableRelation;

public interface ITableDDLService {
	
	public VoResponse createTable(TableDefinition table);
	
	public VoResponse dropTable(String id);
	
	public VoResponse alterTable(TableDefinition table);
	
	public List<TableDefinition> getAll();
	
	public TableDefinition getById(String id);

	VoResponse getTableDefinition(String id);
	
	VoResponse addTableRelation(TableRelation tableRelation);
	
}
