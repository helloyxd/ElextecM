package com.elextec.bi.service;


import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiDataSource;

import java.util.List;


public interface IBiDataSourceService {
	public BiDataSource queryById(String id);
	public VoResponse save(BiDataSource biDataSource);
	public VoResponse update(BiDataSource biDataSource);
	public VoResponse delete(String id);
	public List<BiDataSource> queryAll();
}
