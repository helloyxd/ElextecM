package com.elextec.mdm.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoMain {

	private String name;
	private int count;
	private Map<String,Integer> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Map<String,Integer> getChildren() {
		return children;
	}
	public void setChildren(Map<String,Integer> children) {
		this.children = children;
	}
}
