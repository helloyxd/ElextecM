package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;
import com.elextec.mdm.mapper.ServiceParamFieldDefinedMapper;

/**
 * 主数据表结构映射
 */
public class MdmTableMap extends BasicEntity {

	private String mdmTableId;
	private TableDefinition mdmTableDefined;
	private String mdmFieldId;
	private String bsTableId;
	private ServiceParamTableDefined spTableDefined;
	private String bsFieldId;
	private ServiceParamFieldDefined spFieldDefined;
	private String bsIoType;//数据in out方向，0标识从业务系统出，主数据接受，1表示业务系统接受，主数据出，2标识进出都适用
	
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
	public ServiceParamTableDefined getSpTableDefined() {
		return spTableDefined;
	}
	public void setSpTableDefined(ServiceParamTableDefined spTableDefined) {
		this.spTableDefined = spTableDefined;
	}
	public TableDefinition getMdmTableDefined() {
		return mdmTableDefined;
	}
	public void setMdmTableDefined(TableDefinition mdmTableDefined) {
		this.mdmTableDefined = mdmTableDefined;
	}
	public ServiceParamFieldDefined getSpFieldDefined() {
		return spFieldDefined;
	}
	public void setSpFieldDefined(ServiceParamFieldDefined spFieldDefined) {
		this.spFieldDefined = spFieldDefined;
	}
}
