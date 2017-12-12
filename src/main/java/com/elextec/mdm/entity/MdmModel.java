package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class MdmModel extends BasicEntity{

	private String mdmModel;
	private List<MdmBs> mdmBses;

	public String getMdmModel() {
		return mdmModel;
	}

	public void setMdmModel(String mdmModel) {
		this.mdmModel = mdmModel;
	}

	public List<MdmBs> getMdmBses() {
		return mdmBses;
	}

	public void setMdmBses(List<MdmBs> mdmBses) {
		this.mdmBses = mdmBses;
	}
}
