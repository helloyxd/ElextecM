package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * 主数据管理的业务系统
 * @author zhangkj
 *
 */
public class MdmBs extends BasicEntity{

	private String bsName;//业务系统名称，EHR，明源ERP
	private List<ServiceInterfaceDefined>  siDefineds;

	public String getBsName() {
		return bsName;
	}

	public void setBsName(String bsName) {
		this.bsName = bsName;
	}

	public List<ServiceInterfaceDefined> getSiDefineds() {
		return siDefineds;
	}

	public void setSiDefineds(List<ServiceInterfaceDefined> siDefineds) {
		this.siDefineds = siDefineds;
	}
}
