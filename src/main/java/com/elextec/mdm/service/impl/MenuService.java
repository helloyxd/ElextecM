package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.mapper.MenuMapper;
import com.elextec.mdm.service.IMenuService;
import com.elextec.mdm.vo.VoMenu;

@Service
public class MenuService implements IMenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getAllMenus() {
		List<Menu> list = menuMapper.findAll();
		return list;
	}
	
	@Override
	public List<VoMenu> getAllMenusTree() {
		List<Menu> listMenu = menuMapper.findAllMenusTree();
		List<VoMenu> list = setMenu(listMenu);
		return list;
	}
	
	private List<VoMenu> setMenu(List<Menu> listMenu){
		List<VoMenu> list = new ArrayList<VoMenu>();
		for(Menu menu : listMenu){
			VoMenu e = new VoMenu();
			e.setPath(menu.getMenuUrl());
			e.setComponent(menu.getMenuUrl()==null?"":menu.getMenuUrl().replace("/", ""));
			e.setIconCls(menu.getIcon());
			if(menu.getMenus().size() > 0 && menu.getLevel() < 1000){
				e.setLeaf(true);
				e.setChildren(setMenu(menu.getMenus()));
				//e.setRedirect(menu.getMenuUrl()==null?"":menu.getMenuUrl() + menu.getMenus().get(0).getMenuUrl());
			}
			e.setName(menu.getMenuName());
			list.add(e);
		}
		return list;
	}
	
	@Override
	public List<Menu> getMenus(String level) {
		List<Menu> list = menuMapper.findByLevel(level);
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
