package com.elextec.mdm.common.entity;

import java.util.HashMap;
import java.util.Map;

public class TableDDLMap {

	//oracle 列数据类型
	public final static Map<Integer, String> oracleDataTypeMap = new HashMap<Integer, String>();
	//oracle 列约束
	public final static Map<Integer, String> oracleColumnConstraintMap = new HashMap<Integer, String>();
	
	static{
		oracleDataTypeMap.put(1, "CHAR");//固定长度字符串
		oracleDataTypeMap.put(2, "VARCHAR2");//可变长度
		oracleDataTypeMap.put(3, "DATE");//DD-MM-YY(HH-MI-SS)
		oracleDataTypeMap.put(4, "TIMESTAMP");
		oracleDataTypeMap.put(5, "LONG");//超长字符串
		oracleDataTypeMap.put(6, "BLOB");//超长字符串
		oracleDataTypeMap.put(7, "NUMBER(P,S)");//数字类型，P为整数位，S为小数位
		oracleDataTypeMap.put(8, "DECIMAL(P,S)");//数字类型，P为整数位，S为小数位
		oracleDataTypeMap.put(9, "INTEGER");//整数类型
		oracleDataTypeMap.put(10, "FLOAT");//浮点数
		oracleDataTypeMap.put(11, "REAL");//实数
		
		
		oracleColumnConstraintMap.put(1, "NOT NULL");//非空
		oracleColumnConstraintMap.put(2, "DAFAULT");//default 1，默认值 1
		oracleColumnConstraintMap.put(3, "UNIQUE");//唯一
		oracleColumnConstraintMap.put(4, "PRIMARY KEY");//主键
		oracleColumnConstraintMap.put(5, "FOREIGN KEY");//外键
		//oracleColumnConstraintMap.put(6, "CHECK");
	}
}
