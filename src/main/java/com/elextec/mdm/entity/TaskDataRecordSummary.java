package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class TaskDataRecordSummary extends BasicEntity {

	private String flowId;
	private String modelId;
	private String taskType;
	private int successNum;
	private int failNum;
	private String remark;
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	public int getFailNum() {
		return failNum;
	}
	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
