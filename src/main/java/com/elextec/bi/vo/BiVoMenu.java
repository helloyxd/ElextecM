package com.elextec.bi.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @author zhangkj
 *
 */
public class BiVoMenu {

	private String path;
	private String component;
	private String redirect;
	private String name;
	private String iconCls;
	private boolean leaf;
	private List<BiVoMenu> children;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<BiVoMenu> getChildren() {
		return children;
	}
	public void setChildren(List<BiVoMenu> children) {
		this.children = children;
	}
	public String getRedirect() {
		return redirect;
	}
	@JsonIgnore
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
	
}
