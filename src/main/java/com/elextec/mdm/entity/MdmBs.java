package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * 主数据管理的业务系统
 * @author zhangkj
 *
 */
public class MdmBs extends BasicEntity{

	private String bsName;//业务系统名称，EHR，明源ERP

	public String getBsName() {
		return bsName;
	}

	public void setBsName(String bsName) {
		this.bsName = bsName;
	}
}
