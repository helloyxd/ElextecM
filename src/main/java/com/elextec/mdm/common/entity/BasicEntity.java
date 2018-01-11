package com.elextec.mdm.common.entity;

import java.sql.Date;

import com.elextec.mdm.common.entity.constant.StatusEnum;

public class BasicEntity {
	private String id;
	private Date createTime;
	private String creater;
	private Integer status = StatusEnum.StatusEnable;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
