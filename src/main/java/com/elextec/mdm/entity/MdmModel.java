package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

public class MdmModel extends BasicEntity{

	private String mdmModel;
	private List<MdmBs> mdmBses;
	private List<TableDefinition> tableDefinitions; 
	private List<ServiceInterfaceDefined>  siDefineds;

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

	public List<TableDefinition> getTableDefinitions() {
		return tableDefinitions;
	}

	public void setTableDefinitions(List<TableDefinition> tableDefinitions) {
		this.tableDefinitions = tableDefinitions;
	}

	public List<ServiceInterfaceDefined> getSiDefineds() {
		return siDefineds;
	}

	public void setSiDefineds(List<ServiceInterfaceDefined> siDefineds) {
		this.siDefineds = siDefineds;
	}
}
