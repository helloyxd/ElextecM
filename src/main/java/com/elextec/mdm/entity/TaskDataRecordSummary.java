package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * 流程数据处理汇总，根据批量发送统计
 * @author zhangkj
 *
 */
public class TaskDataRecordSummary extends BasicEntity {

	private String flowId;
	private String modelId;
	private MdmModel model;
	private String bsId;
	private String taskType;//任务类型,0push,1pull,2receive(系统推送过来)
	private int successNum;
	private int failNum;
	private String remark;//数据说明，可以记录一些异常信息或者其他
	private List<TaskDataRecordDetail> details;
	//state 数据状态，1发送成功，0发送失败，2部分成功，部分失败
	
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
	public String getBsId() {
		return bsId;
	}
	public void setBsId(String bsId) {
		this.bsId = bsId;
	}
	public List<TaskDataRecordDetail> getDetails() {
		return details;
	}
	public void setDetails(List<TaskDataRecordDetail> details) {
		this.details = details;
	}
	public MdmModel getModel() {
		return model;
	}
	public void setModel(MdmModel model) {
		this.model = model;
	}
	
	
}
