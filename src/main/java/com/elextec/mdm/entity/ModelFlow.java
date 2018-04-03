package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class ModelFlow extends BasicEntity{

	private String activitiId;//流程模版标识
	private String modelId;//ServiceInterface_Defined表主键
	private String operationType;//操作类型，0拉取，1提送
	
	public String getActivitiId() {
		return activitiId;
	}
	public void setActivitiId(String activitiId) {
		this.activitiId = activitiId;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	
}
