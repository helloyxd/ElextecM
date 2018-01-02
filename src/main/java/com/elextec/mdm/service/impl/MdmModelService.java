package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MdmBsMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.MenuMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IMdmModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	@Transactional
	public VoResponse addModel(MdmModel model) {
		VoResponse voRes = new VoResponse();
		List<MdmModel> list = mdmModelMapper.findByName(model.getMdmModel());
		if(list != null && list.size() > 0){
			voRes.setFail(voRes);
			voRes.setMessage("模块名称已经存在");
			return voRes;
		}
		model.setStatus(StatusEnum.StatusEnable);
		model.setCreater(getUserName());
		mdmModelMapper.insert(model);
		List<Menu> menus = menuMapper.findByName("主数据管理");
		Menu menu = new Menu();
		menu.setMenuName(model.getMdmModel());
		menu.setMenuUrl("");
		menu.setMethod("");
		menu.setParentId(menus.get(0).getId());
		menu.setLevel(100);
		menu.setSortOrder(1);
		menu.setCreater("sys");
		menu.setStatus(StatusEnum.StatusEnable);
		menuMapper.insert(menu);
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
		mdmBsMapper.insert(bs);
		return voRes;
	}

	@Override
	public List<MdmModel> getAll() {
		List<MdmModel> list = mdmModelMapper.findAll();
		return list;
	}

}
