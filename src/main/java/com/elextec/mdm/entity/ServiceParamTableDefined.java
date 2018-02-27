package com.elextec.mdm.entity;

import java.util.List;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class ServiceParamTableDefined  extends BasicEntity{

	private String paramId;
	private String tableName;//参数结构表	
	private String relationType;//关联类型，即表与主表的管理类型，10：1对1，2：多对1
	private String parentId;
	private List<ServiceParamFieldDefined> sParamFieldDefineds;
	
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<ServiceParamFieldDefined> getsParamFieldDefineds() {
		return sParamFieldDefineds;
	}
	public void setsParamFieldDefineds(List<ServiceParamFieldDefined> sParamFieldDefineds) {
		this.sParamFieldDefineds = sParamFieldDefineds;
	}

	
}
