package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * 主数据表结构映射
 */
public class MdmTableMap extends BasicEntity {

	private String mdmTableId;
	private TableDefinition tableDefined;
	private String mdmFieldId;
	private String bsTableId;
	private String bsFieldId;
	private String bsIoType;
	
	public String getMdmTableId() {
		return mdmTableId;
	}
	public void setMdmTableId(String mdmTableId) {
		this.mdmTableId = mdmTableId;
	}
	public String getMdmFieldId() {
		return mdmFieldId;
	}
	public void setMdmFieldId(String mdmFieldId) {
		this.mdmFieldId = mdmFieldId;
	}
	public String getBsTableId() {
		return bsTableId;
	}
	public void setBsTableId(String bsTableId) {
		this.bsTableId = bsTableId;
	}
	public String getBsFieldId() {
		return bsFieldId;
	}
	public void setBsFieldId(String bsFieldId) {
		this.bsFieldId = bsFieldId;
	}
	public String getBsIoType() {
		return bsIoType;
	}
	public void setBsIoType(String bsIoType) {
		this.bsIoType = bsIoType;
	}
	public TableDefinition getTableDefined() {
		return tableDefined;
	}
	public void setTableDefined(TableDefinition tableDefined) {
		this.tableDefined = tableDefined;
	}
}
