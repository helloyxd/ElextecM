package com.elextec.bi.controller;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiDataSource;
import com.elextec.bi.service.IBiDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("bi/dataSource")
public class BiDataSourceController {

	@Autowired
	private IBiDataSourceService biDataSourceService;

	@GetMapping("/getAll")
	public Object getAll() {
		VoResponse voRes = new VoResponse();
		voRes.setData(biDataSourceService.queryAll());
		return voRes;
	}

	@RequestMapping(value = "/addInfo", method = RequestMethod.POST)
	public Object add(@RequestBody BiDataSource biDataSource) {
		VoResponse voRes = biDataSourceService.save(biDataSource);
		return voRes;
	}

	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	public Object queryById(String id) {
		VoResponse voRes = new VoResponse();
		BiDataSource biDataSource = biDataSourceService.queryById(id);
		if(biDataSource != null){
			voRes.setData(biDataSource);
		}else{
			voRes.setFail(voRes);
			voRes.setMessage("dataSource is null");
		}
		return voRes;
	}

	@RequestMapping(value ="/delInfo",method = RequestMethod.POST)
	public Object del(String id){
		return biDataSourceService.delete(id);
	}

	@RequestMapping(value ="/updateInfo",method = RequestMethod.POST)
	public Object update(@RequestBody BiDataSource biDataSource){
		return biDataSourceService.update(biDataSource);
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user = "jybi_admin";
			String pass = "bz123456";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@RequestMapping(value ="/test",method = RequestMethod.POST)
	public Object test(String table){
		Connection conn = getConnection();
		String sql = "select * from " + table;
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得所有列的数目及实际列数
				int columnCount = data.getColumnCount();
                // 获得指定列的列名
				String columnName = data.getColumnName(i);
                // 获得指定列的列值
				int columnType = data.getColumnType(i);
                // 获得指定列的数据类型名
				String columnTypeName = data.getColumnTypeName(i);
                // 所在的Catalog名字
				String catalogName = data.getCatalogName(i);
                // 对应数据类型的类
				String columnClassName = data.getColumnClassName(i);
                // 在数据库中类型的最大字符个数
				int columnDisplaySize = data.getColumnDisplaySize(i);
                // 默认的列的标题
				String columnLabel = data.getColumnLabel(i);
                // 获得列的模式
				String schemaName = data.getSchemaName(i);
                // 某列类型的精确度(类型的长度)
				int precision = data.getPrecision(i);
                // 小数点后的位数
				int scale = data.getScale(i);
                // 获取某列对应的表名
				String tableName = data.getTableName(i);
                // 是否自动递增
				boolean isAutoInctement = data.isAutoIncrement(i);
                // 在数据库中是否为货币型
				boolean isCurrency = data.isCurrency(i);
                // 是否为空
				int isNullable = data.isNullable(i);
				// 是否为只读
				boolean isReadOnly = data.isReadOnly(i);
                // 能否出现在where中
				boolean isSearchable = data.isSearchable(i);
				System.out.println(columnCount);
				System.out.println("获得列" + i + "的字段名称:" + columnName);
				System.out.println("获得列" + i + "的类型,返回SqlType中的编号:"+ columnType);
				System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
				System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
				System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
				System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
				System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
				System.out.println("获得列" + i + "的模式:" + schemaName);
				System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
				System.out.println("获得列" + i + "小数点后的位数:" + scale);
				System.out.println("获得列" + i + "对应的表名:" + tableName);
				System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
				System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
				System.out.println("获得列" + i + "是否为空:" + isNullable);
				System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
				System.out.println("获得列" + i + "能否出现在where中:"+ isSearchable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



}
