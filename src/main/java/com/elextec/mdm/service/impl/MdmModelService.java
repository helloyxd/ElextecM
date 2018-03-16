package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmBsMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IMdmModelService;

/**
 * @author zhangkj
 *
 */
@Service
public class MdmModelService extends BaseService implements IMdmModelService {

	@Autowired
	private MdmBsMapper mdmBsMapper;
	
	@Autowired
	private MdmModelMapper mdmModelMapper;
	
	@Override
	@Transactional
	public VoResponse addModel(MdmModel model) {
		VoResponse voRes = new VoResponse();
		if(model.getId() == null){
			List<MdmModel> list = mdmModelMapper.findByName(model.getMdmModel());
			if(list != null && list.size() > 0){
				voRes.setFail(voRes);
				voRes.setMessage("模块名称已经存在");
				return voRes;
			}
			model.setCreater(getUserName());
			mdmModelMapper.insert(model);
		}else{
			MdmModel oldmodel = mdmModelMapper.findById(model.getId());
			
		}
		return voRes;
	}
	
	@Override
	public VoResponse delModel(String modelId) {
		VoResponse voRes = new VoResponse();
		MdmModel model = mdmModelMapper.findById(modelId);
		if(model == null){
			voRes.setNull(voRes);
			return voRes;
		}
		List<TableDefinition> listTables = model.getTableDefinitions();
		if(listTables != null && listTables.size() > 0){
			voRes.setFail(voRes);
			voRes.setMessage("请先删除该模块下自定义表");
			return voRes;
		}
		mdmModelMapper.del(modelId);
		return voRes;
	}
	
	@Override
	public VoResponse addBs(MdmBs bs){
		VoResponse voRes = new VoResponse();
		List<MdmBs> list = mdmBsMapper.findByName(bs.getBsName());
		if(list.size() == 0){
			bs.setCreater(getUserName());
			mdmBsMapper.insert(bs);
		}else{
			voRes.setFail(voRes);
			voRes.setMessage("业务系统名字已经存在");
		}
		return voRes;
	}

	@Override
	public List<MdmModel> getAll() {
		List<MdmModel> list = mdmModelMapper.findAll();
		return list;
	}
	
	@Override
	public List<MdmModel> getAllInfo() {
		List<MdmModel> list = mdmModelMapper.findAllInfo();
		return list;
	}

	@Override
	public List<MdmBs> getAllBs() {
		List<MdmBs> list = mdmBsMapper.findAll();
		return list;
	}

	@Override
	public VoResponse delBs(String bsId) {
		VoResponse voRes = new VoResponse();
		mdmBsMapper.del(bsId);
		return voRes;
	}

	@Override
	public MdmModel getById(String id) {
		MdmModel model = mdmModelMapper.findById(id);
		return model;
	}

	@Override
	public MdmBs getBsById(String id) {
		List<MdmBs> bs = mdmBsMapper.findById(id);
		return bs.get(0);
	}

}
