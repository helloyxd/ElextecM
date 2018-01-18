package com.elextec.bi.service.impl;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiReportConfig;
import com.elextec.bi.mapper.BiReportConfigMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiReportConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiReportConfigService extends BiBaseService implements IBiReportConfigService{

	@Autowired
	private BiReportConfigMapper biReportConfigMapper;


	@Override
	public List<BiReportConfig> findAll() {
		return biReportConfigMapper.findAll();
	}

	@Override
	public BiReportConfig findById(String id) {
		return biReportConfigMapper.findById(id);
	}

	@Override
	public VoResponse delReportConfig(String id) {
		VoResponse VoRes = new VoResponse();
		biReportConfigMapper.delete(id);
		return VoRes;
	}

	@Override
	public VoResponse addReportConfig(BiReportConfig biReportConfig) {
		VoResponse VoRes = new VoResponse();
		biReportConfigMapper.insert(biReportConfig);
		return VoRes;
	}

	@Override
	public VoResponse updateReportConfig(BiReportConfig biReportConfig) {
		VoResponse VoRes = new VoResponse();
		biReportConfigMapper.update(biReportConfig);
		return VoRes;
	}
}
