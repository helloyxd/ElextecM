package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class MdmConfig extends BasicEntity{

	private String configName;
	private String configValue;
	private String remark;
	
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
