package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class TableRelation extends BasicEntity {

	private String table1;
	private String table2;
	private int relation;
	private String foreignKey1;
	private String foreignKey2;
	private String mutiRelationTable;
	
	public String getTable1() {
		return table1;
	}
	public void setTable1(String table1) {
		this.table1 = table1;
	}
	public String getTable2() {
		return table2;
	}
	public void setTable2(String table2) {
		this.table2 = table2;
	}
	public int getRelation() {
		return relation;
	}
	public void setRelation(int relation) {
		this.relation = relation;
	}
	public String getForeignKey1() {
		return foreignKey1;
	}
	public void setForeignKey1(String foreignKey1) {
		this.foreignKey1 = foreignKey1;
	}
	public String getForeignKey2() {
		return foreignKey2;
	}
	public void setForeignKey2(String foreignKey2) {
		this.foreignKey2 = foreignKey2;
	}
	public String getMutiRelationTable() {
		return mutiRelationTable;
	}
	public void setMutiRelationTable(String mutiRelationTable) {
		this.mutiRelationTable = mutiRelationTable;
	}
	
	
	
}
