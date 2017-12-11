package com.elextec.mdm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.service.IMenuService;

@Service
public class MenuService implements IMenuService{

	@Autowired
	private IMenuService menuService;
	
	@Override
	public Object getAllMenus() {
		return null;
	}
	
	

	@Override
	public VoResponse delMenu(int id) {
		return null;
	}

	@Override
	public VoResponse addMenu(Menu menu) {
		return null;
	}

	@Override
	public VoResponse updateMenu(Menu menu) {
		return null;
	}
	
	

}
