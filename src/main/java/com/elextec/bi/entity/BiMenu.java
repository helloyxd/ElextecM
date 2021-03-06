package com.elextec.bi.entity;

import com.elextec.bi.common.entity.BasicEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BiMenu extends BasicEntity{

	@JsonProperty("label")
	private String menuName;
	private String menuUrl;
	private String method;
	private String icon;
	private String parentId;
	private Integer level;
	private Integer sortOrder;
	private String remark;
	private List<BiMenu> menus;
	
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<BiMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<BiMenu> menus) {
		this.menus = menus;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
