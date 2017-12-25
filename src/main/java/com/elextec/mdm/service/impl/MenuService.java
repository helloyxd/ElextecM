package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.mapper.MenuMapper;
import com.elextec.mdm.service.IMenuService;

@Service
public class MenuService implements IMenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public Object getAllMenus() {
		List<Menu> list = menuMapper.findAll();
		return list;
	}
	
	@Override
	public VoResponse delMenu(String id) {
		VoResponse voRes = new VoResponse();
		menuMapper.delById(id);
		return voRes;
	}

	@Override
	public VoResponse addMenu(Menu menu) {
		VoResponse voRes = new VoResponse();
		menu.setStatus(StatusEnum.StatusEnable);
		menuMapper.insert(menu);
		return voRes;
	}

	@Override
	public VoResponse updateMenu(Menu menu) {
		VoResponse voRes = new VoResponse();
		menuMapper.update(menu);
		return voRes;
	}
	
	

}
