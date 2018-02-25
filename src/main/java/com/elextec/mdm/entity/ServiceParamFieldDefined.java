package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class ServiceParamFieldDefined  extends BasicEntity{

	private String tableId;
	private String fieldName;
	private String fieldType;
	
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	
}
