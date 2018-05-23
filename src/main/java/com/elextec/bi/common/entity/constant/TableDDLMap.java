package com.elextec.bi.common.entity.constant;

import java.util.HashMap;
import java.util.Map;

public class TableDDLMap {

	//oracle 列数据类型
	public final static Map<String, String> oracleDataTypeMap = new HashMap<String, String>();
	//oracle 列约束
	public final static Map<String, String> oracleColumnConstraintMap = new HashMap<String, String>();
	
	static{
		oracleDataTypeMap.put("CHAR", "CHAR");//固定长度字符串
		oracleDataTypeMap.put("VARCHAR2", "VARCHAR2");//可变长度
		oracleDataTypeMap.put("DATE", "DATE");//DD-MM-YY(HH-MI-SS)
		oracleDataTypeMap.put("TIMESTAMP(6)", "TIMESTAMP");
		oracleDataTypeMap.put("LONG", "LONG");//超长字符串
		oracleDataTypeMap.put("BLOB", "BLOB");//超长字符串
		oracleDataTypeMap.put("NUMBER", "NUMBER(P,S)");//数字类型，P为整数位，S为小数位
		//oracleDataTypeMap.put("DECIMAL", "DECIMAL(P,S)");//数字类型，P为整数位，S为小数位
		oracleDataTypeMap.put("INTEGER", "INTEGER");//整数类型
		oracleDataTypeMap.put("FLOAT", "FLOAT");//浮点数
		oracleDataTypeMap.put("REAL", "REAL");//实数
		
		
		oracleColumnConstraintMap.put("N", "NOT NULL");//非空
		oracleColumnConstraintMap.put("Y", "NULL");//非空
		oracleColumnConstraintMap.put("DAFAULT", "DAFAULT");//default 1，默认值 1
		oracleColumnConstraintMap.put("UNIQUE", "UNIQUE");//唯一
		oracleColumnConstraintMap.put("P", "PRIMARY KEY");//主键
		oracleColumnConstraintMap.put("F", "FOREIGN KEY");//外键
		//oracleColumnConstraintMap.put(6, "CHECK");
	}
}
