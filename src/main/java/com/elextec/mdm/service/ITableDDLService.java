package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.entity.TableRelation;

public interface ITableDDLService {
	
	public VoResponse createTable(TableDefinition table);
	
	public VoResponse dropTable(String id);
	
	public VoResponse alterTable(TableDefinition table);
	
	public List<TableDefinition> getAll();
	
	public TableDefinition getById(String id);
	
	void setColumnsDefinition(TableDefinition table);

	VoResponse getTableDefinition(String id);
	
	VoResponse addTableRelation(TableRelation tableRelation);
	
	public List<TableRelation> getTableRelations();
	
	public List<TableDefinition> getByModelId(String modelId);
	
	public VoResponse getDefinedData(MdmModel model);
	
	public VoResponse postDefinedData(TableDefinition table, Map<String,String> map);
	
	public VoResponse delDefinedData(TableDefinition table, String id);
	
	public VoResponse updateDefinedData(TableDefinition table, Map<String,String> map);
}
