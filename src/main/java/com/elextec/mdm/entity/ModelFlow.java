package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class ModelFlow extends BasicEntity{

	private String activitiId;//流程模版标识
	private String modelId;//ServiceInterface_Defined表主键
	private String operationType;//操作类型，0拉取，1提送
	//activiti_model_id
	private String activitiModelId;//流程模版再activiti系统中的主键		
	//state 状态，0正常启用，1禁用，2删除
	
	private String flowChartContent;
	
	
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
	public String getActivitiModelId() {
		return activitiModelId;
	}
	public void setActivitiModelId(String activitiModelId) {
		this.activitiModelId = activitiModelId;
	}
	public String getFlowChartContent() {
		return flowChartContent;
	}
	public void setFlowChartContent(String flowChartContent) {
		this.flowChartContent = flowChartContent;
	}
	
	
}
