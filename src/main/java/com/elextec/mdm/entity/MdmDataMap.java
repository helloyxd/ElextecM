package com.elextec.mdm.entity;

import java.sql.Date;

import com.elextec.mdm.common.entity.BasicEntity;

public class MdmDataMap extends BasicEntity {

	private String mdmDataId;
	private String bsDataId;
	private String modelId;
	private MdmModel model;
	private String bsId;
	private String modifier;
	private Date modifierTime;
	
	public String getMdmDataId() {
		return mdmDataId;
	}
	public void setMdmDataId(String mdmDataId) {
		this.mdmDataId = mdmDataId;
	}
	public String getBsDataId() {
		return bsDataId;
	}
	public void setBsDataId(String bsDataId) {
		this.bsDataId = bsDataId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getBsId() {
		return bsId;
	}
	public void setBsId(String bsId) {
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
	public MdmModel getModel() {
		return model;
	}
	public void setModel(MdmModel model) {
		this.model = model;
	}
	
}
