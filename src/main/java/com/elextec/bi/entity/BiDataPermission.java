package com.elextec.bi.entity;

import com.elextec.bi.common.entity.BasicEntity;

/**
 * 数据权限实体类
 * */
public class BiDataPermission extends BasicEntity{

	private String id;
	private String dataPermissionName;
	private String dataPermissionType;
	private String sql;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getDataPermissionName() {
		return dataPermissionName;
	}

	public void setDataPermissionName(String dataPermissionName) {
		this.dataPermissionName = dataPermissionName;
	}

	public String getDataPermissionType() {
		return dataPermissionType;
	}

	public void setDataPermissionType(String dataPermissionType) {
		this.dataPermissionType = dataPermissionType;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
