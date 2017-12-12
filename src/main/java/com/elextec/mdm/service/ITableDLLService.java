package com.elextec.mdm.service;

import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.TableDefinition;

public interface ITableDLLService {
	public VoResponse createTable(TableDefinition table);
	public void dropTable(String tableName);
	public void alterTable(TableDefinition table);
	public List<Map> getDBDataType();
}
