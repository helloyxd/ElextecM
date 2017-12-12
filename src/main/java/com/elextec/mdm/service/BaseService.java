package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;

public interface BaseService<T> {

	public List<T> getAll();
	
	public T getById(int id);
	
	public VoResponse delById(int id);
	
	public VoResponse add(T t);
	
	public VoResponse update(T t);
}
