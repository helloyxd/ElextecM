package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.mapper.MdmBsMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.service.IMdmModelService;

/**
 * @author zhangkj
 *
 */
@Service
public class MdmModelService implements IMdmModelService {

	@Autowired
	private MdmBsMapper mdmBsMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;

	@Override
	public VoResponse addModel(MdmModel model) {
		VoResponse voRes = new VoResponse();
		mdmModelMapper.insert(model);
		return voRes;
	}
	
	@Override
	public VoResponse addBs(MdmBs bs){
		VoResponse voRes = new VoResponse();
		mdmBsMapper.insert(bs);
		return voRes;
	}

	@Override
	public List<MdmModel> getAll() {
		List<MdmModel> list = mdmModelMapper.findAll();
		return list;
	}
	
}
