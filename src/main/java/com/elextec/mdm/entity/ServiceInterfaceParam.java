package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class ServiceInterfaceParam extends BasicEntity{
	
	private String serviceDefinedId;
	private String ioType;//输入输出类别，0输入，1输出
	private String dataType;//参数数据类型，int,string等基本型以及complex复杂型
	private String tableId;
	private String tableName;//复杂类型表名
	private List<ServiceParamTableDefined> sParamTableDefineds;
	
	public String getServiceDefinedId() {
		return serviceDefinedId;
	}
	public void setServiceDefinedId(String serviceDefinedId) {
		this.serviceDefinedId = serviceDefinedId;
	}
	public String getIoType() {
		return ioType;
	}
	public void setIoType(String ioType) {
		this.ioType = ioType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public List<ServiceParamTableDefined> getsParamTableDefineds() {
		return sParamTableDefineds;
	}
	public void setsParamTableDefineds(List<ServiceParamTableDefined> sParamTableDefineds) {
		this.sParamTableDefineds = sParamTableDefineds;
	}
	
}
