package com.elextec.mdm.common.entity;

import java.sql.Date;

public class BasicEntity {
	private Date createDate;
	private Date modifyDate;
	private Integer disable; //0启动，1启用
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getDisable() {
		return disable;
	}
	public void setDisable(Integer disable) {
		this.disable = disable;
	}
	
	
}
