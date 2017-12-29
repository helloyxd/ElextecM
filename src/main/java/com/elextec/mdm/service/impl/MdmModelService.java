package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmBs;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.mapper.MdmBsMapper;
import com.elextec.mdm.mapper.MdmModelMapper;
import com.elextec.mdm.mapper.MenuMapper;
import com.elextec.mdm.service.IMdmModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	@Transactional
	public VoResponse addModel(MdmModel model) {
		VoResponse voRes = new VoResponse();
		mdmModelMapper.insert(model);
		List<Menu> menus = menuMapper.findByName("主数据管理");
		Menu menu = new Menu();
		menu.setMenuName(model.getMdmModel());
		menu.setParentId(menus.get(0).getId());
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(mdmModelMapper.findAll()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu.setCreater("sys");
		menu.setStatus(0);
		menuMapper.insert(menu);
		if(menus.get(10).getId() == null){
		}
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
