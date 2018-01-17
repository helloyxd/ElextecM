package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class WsDefined extends BasicEntity{

	private String wsurl;
	private String remark;
	private String bsId;//业务系统
	
	
	public String getWsurl() {
		return wsurl;
	}
	public void setWsurl(String wsurl) {
		this.wsurl = wsurl;
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
	
}
