package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class ServiceInterfaceDefined extends BasicEntity{

	private String type;//接口类型,0 webservice,1 存储过程
	private String wsdlLocation;//WSDL地址，如果接口是存储过程，需要填入数据库连接字符串
	private String dburl;
	private String remark;//描述
	private String username;
	private String password;
	private String modelId;//所属MDM模块
	private String bsId;//所属业务系统
	private String operationType;//操作类型，0拉取，1提送
	private String operation;//webservice的接口方法或者存储过程方法
	private String wsbinding;//webservice binding
	private String bingNamespace;//webservice binding namespance
	private String operationNamespace;//webservice operation namespance
	private List<ServiceInterfaceParam> siParams;
	private boolean isDataSource;//是否数据源
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWsdlLocation() {
		return wsdlLocation;
	}
	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}
	public String getDburl() {
		return dburl;
	}
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getWsbinding() {
		return wsbinding;
	}
	public void setWsbinding(String wsbinding) {
		this.wsbinding = wsbinding;
	}
	public String getBingNamespace() {
		return bingNamespace;
	}
	public void setBingNamespace(String bingNamespace) {
		this.bingNamespace = bingNamespace;
	}
	public String getOperationNamespace() {
		return operationNamespace;
	}
	public void setOperationNamespace(String operationNamespace) {
		this.operationNamespace = operationNamespace;
	}
	public List<ServiceInterfaceParam> getSiParams() {
		return siParams;
	}
	public void setSiParams(List<ServiceInterfaceParam> siParams) {
		this.siParams = siParams;
	}
	public String getBsId() {
		return bsId;
	}
	public void setBsId(String bsId) {
		this.bsId = bsId;
	}
	public boolean isDataSource() {
		return isDataSource;
	}
	public void setDataSource(boolean isDataSource) {
		this.isDataSource = isDataSource;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
