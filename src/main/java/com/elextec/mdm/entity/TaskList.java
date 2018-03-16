package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class TaskList extends BasicEntity {

	private String flowId;//流程ID
	private String flowType;//流程类型，0; MDM推动到业务系统，1
	private String dataId;//数据ID
	private String bsId;
	private String modelId;
	private String remark;
	private String currentUser;
	private String lastUser;
	private String currentNode;
	private String lastNode;
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getBsId() {
		return bsId;
	}
	public void setBsId(String bsId) {
		this.bsId = bsId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public String getLastUser() {
		return lastUser;
	}
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	public String getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}
	public String getLastNode() {
		return lastNode;
	}
	public void setLastNode(String lastNode) {
		this.lastNode = lastNode;
	}
	
}
