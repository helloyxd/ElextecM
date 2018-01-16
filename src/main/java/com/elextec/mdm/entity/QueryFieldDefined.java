/**
 * 
 */
package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class QueryFieldDefined extends BasicEntity{

	private String tableId;
	private String field;
	private String fieldType;//TableDDLMap.columnTypeMap
	private String sortOrder;
	private String columnSpan;
	private TableDefinition tableDefined;
	
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getColumnSpan() {
		return columnSpan;
	}
	public void setColumnSpan(String columnSpan) {
		this.columnSpan = columnSpan;
	}
	public TableDefinition getTableDefined() {
		return tableDefined;
	}
	public void setTableDefined(TableDefinition tableDefined) {
		this.tableDefined = tableDefined;
	}
	
	
}
