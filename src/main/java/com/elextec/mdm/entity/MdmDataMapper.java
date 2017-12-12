package com.elextec.mdm.entity;

import java.sql.Date;

import com.elextec.mdm.common.entity.BasicEntity;

public class MdmDataMapper extends BasicEntity {

	private int mdmDataId;
	private int bsDataId;
	private int modelId;
	private int bsId;
	private String modifier;
	private Date modifierTime;
	
	
	public int getMdmDataId() {
		return mdmDataId;
	}
	public void setMdmDataId(int mdmDataId) {
		this.mdmDataId = mdmDataId;
	}
	public int getBsDataId() {
		return bsDataId;
	}
	public void setBsDataId(int bsDataId) {
		this.bsDataId = bsDataId;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getBsId() {
		return bsId;
	}
	public void setBsId(int bsId) {
		this.bsId = bsId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifierTime() {
		return modifierTime;
	}
	public void setModifierTime(Date modifierTime) {
		this.modifierTime = modifierTime;
	}
	
	
	
}
