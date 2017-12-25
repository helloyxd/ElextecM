package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

public class TableRelation extends BasicEntity {

	private String table1;//表1 ID
	private TableDefinition tableDefinition1;
	private String table2;
	private TableDefinition tableDefinition2;
	private int relation;
	private String foreignKey1;//表1的外键字段，即表2的主键，默认表名_id
	private String foreignKey2;//表2的外键字段，即表1的主键，默认表名_id
	private String mutiRelationTable;//N对N时的中间表
	
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
	public TableDefinition getTableDefinition1() {
		return tableDefinition1;
	}
	public void setTableDefinition1(TableDefinition tableDefinition1) {
		this.tableDefinition1 = tableDefinition1;
	}
	public TableDefinition getTableDefinition2() {
		return tableDefinition2;
	}
	public void setTableDefinition2(TableDefinition tableDefinition2) {
		this.tableDefinition2 = tableDefinition2;
	}
	
	
	
}
