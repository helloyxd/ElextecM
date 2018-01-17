package com.elextec.mdm.entity;

import com.elextec.mdm.common.entity.BasicEntity;

/**
 * @author zhangkj
 *
 */
public class WsParameterDefined  extends BasicEntity{

	private String wsMethodId;
	private String parameterName;//参数名
	private String parameterType;//参数数据类型
	private String parentId;
	private WsParameterDefined children;
	
	public String getWsMethodId() {
		return wsMethodId;
	}
	public void setWsMethodId(String wsMethodId) {
		this.wsMethodId = wsMethodId;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public WsParameterDefined getChildren() {
		return children;
	}
	public void setChildren(WsParameterDefined children) {
		this.children = children;
	}
	
	
}
