package com.elextec.mdm.vo;

import java.util.List;

public class VoDataMap {

	private String mdmTableId;
	private String bsTableId;
	private List<VoLineData> lineData;
	
	
	public String getMdmTableId() {
		return mdmTableId;
	}
	public void setMdmTableId(String mdmTableId) {
		this.mdmTableId = mdmTableId;
	}
	public String getBsTableId() {
		return bsTableId;
	}
	public void setBsTableId(String bsTableId) {
		this.bsTableId = bsTableId;
	}
	public List<VoLineData> getLineData() {
		return lineData;
	}
	public void setLineData(List<VoLineData> lineData) {
		this.lineData = lineData;
	}
	
	
}
