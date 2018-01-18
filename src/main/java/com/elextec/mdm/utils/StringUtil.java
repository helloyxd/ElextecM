package com.elextec.mdm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.elextec.mdm.entity.TableDefinition;

public class StringUtil {

	private static String [] sqlKeyword= {
		"ACCESS","ADD","ALL","ALTER","AND","ANY","AS","ASC","AUDIT","BETWEEN","BY","CHAR","CHECK","CLUSTER",
		"COLUMN","COMMENT","COMPRESS","CONNECT","CREATE","CURRENT","DATE","DECIMAL","DEFAULT","DELETE","DESC",
		"DISTINCT","DROP","ELSE","EXCLUSIVE","EXISTS","FILE","FLOAT","FOR","FROM","GRANT","GROUP","HAVING",
		"IDENTIFIED","IMMEDIATE","IN","INCREMENT","INDEX","INITIAL","INSERT","INTEGER","INTERSECT","INTO","IS",
		"LEVEL","LIKE","LOCK","LONG","MAXEXTENTS","MINUS","MLSLABEL","MODE","MODIFY","NOAUDIT","NOCOMPRESS",
		"NOT","NOWAIT","NULL","NUMBER","OF","OFFLINE","ON","ONLINE","OPTION","OR","ORDER","P","CTFREE","PRIOR",
		"PRIVILEGES","PUBLIC","RAW","RENAME","RESOURCE","REVOKE","ROW","ROWID","ROWNUM","ROWS","SELECT",
		"SESSION","SET","SHARE","SIZE","SMALLINT","START","SUCCESSFUL","SYNONYM", "SYSDATE", "TABLE", "THEN",
		"TO", "TRIGGER", "UID", "UNION", "UNIQUE", "UPDATE", "USER", "VALIDATE", "VALUES", "VARCHAR", "VARCHAR2",
		"VIEW", "WHENEVER", "WHERE", "WITH"
		};
	/**
	 * 根据表定义生产表名规则
	 * @param tableDefinition
	 * @return
	 */
	public static String tableNameGenerationRule(TableDefinition tableDefinition){
		
		return null;
	}
	
	/**
	 * 验证表名/字段  字母开头，允许1-30字节，允许字母数字下划线
	 * @param tableName
	 * @return
	 */
	public static boolean validateTableName(String tableName){
		String regEx ="^[a-zA-Z][a-zA-Z0-9_]{0,29}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(tableName);
		return m.find();
	}
	
	/**
	 * 验证表名/字段   是否oracle关键字
	 * @param tableName
	 * @return
	 */
	public static boolean validateTableNameKeyWord(String tableName){
		for(String s : sqlKeyword){
			if(s.equals(tableName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证字符串只允许字母和数字
	 * @param str
	 * @return
	 */
	public static boolean validateString(String str){
		String regEx ="[^a-zA-Z0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**
	 * 验证字符串是否包含特殊字符
	 * @param str
	 * @return
	 */
	public static boolean validateString2(String str){
		String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**
	 * 字母开头，允许1-30字节，允许字母数字下划线
	 * @param str
	 * @return
	 */
	public static boolean validateString3(String str){
		String regEx ="^[a-zA-Z][a-zA-Z0-9_]{0,29}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.validateString("111"));
		System.out.println(StringUtil.validateString2("2"));
		System.out.println(StringUtil.validateString3("aaa"));
		System.out.println(StringUtil.validateString3("a23456789012345678901234567890"));
	}
	
}
