package com.elextec.bi.service.impl;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.constant.StatusEnum;
import com.elextec.bi.entity.BiMenu;
import com.elextec.bi.mapper.BiMenuMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiMenuService;
import com.elextec.bi.vo.BiVoMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BiMenuService extends BiBaseService implements IBiMenuService{

	@Autowired
	private BiMenuMapper biMenuMapper;
	
	@Override
	public List<BiMenu> getAllMenus() {
		List<BiMenu> list = biMenuMapper.findAll();
		return list;
	}
	
	@Override
	public List<BiVoMenu> getAllMenusTree() {
		List<BiMenu> listMenu = biMenuMapper.findAllMenusTree();
		List<BiVoMenu> list = setMenu(listMenu);
		return list;
	}
	
	private List<BiVoMenu> setMenu(List<BiMenu> listMenu){
		List<BiVoMenu> list = new ArrayList<BiVoMenu>();
		for(BiMenu menu : listMenu){
			BiVoMenu e = new BiVoMenu();
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
	public List<BiMenu> getMenus(String level) {
		List<BiMenu> list = biMenuMapper.findAllByLevel(level);
		return list;
	}
	
	@Override
	public VoResponse delMenu(String id) {
		VoResponse voRes = new VoResponse();
		BiMenu menu = biMenuMapper.findById(id);
		if(menu == null || menu.getMenus().size() > 0){
			voRes.setFail(voRes);
			return voRes;
		}
		biMenuMapper.delById(id);
		return voRes;
	}

	@Override
	public VoResponse addMenu(BiMenu menu) {
		VoResponse voRes = new VoResponse();
		if(menu.getMenuName() == null || menu.getMenuName().equals("")){
			voRes.setNull(voRes);
			voRes.setMessage("菜单名称不能为空");
			return voRes;
		}
		if(menu.getParentId() != null){
			BiMenu perMenu = biMenuMapper.findById(menu.getParentId());
			if(perMenu == null){
				voRes.setNull(voRes);
				return voRes;
			}
			if(menu.getLevel() == null || menu.getLevel() != 1000){
				menu.setLevel(perMenu.getLevel() + 1);
			}
		}else{
			menu.setLevel(0);
		}
		menu.setCreater(getUserName());
		menu.setStatus(StatusEnum.StatusEnable);
		biMenuMapper.insert(menu);
		return voRes;
	}

	@Override
	public VoResponse updateMenu(BiMenu menu) {
		VoResponse voRes = new VoResponse();
		BiMenu oldMenu = biMenuMapper.findById(menu.getId());
		if(oldMenu == null){
			voRes.setNull(voRes);
			return voRes;
		}
		if(oldMenu.getMenuName().equals("主数据管理") && oldMenu.getLevel() == 1){
			voRes.setFail(voRes);
			return voRes;
		}
		biMenuMapper.update(menu);
		return voRes;
	}
	
	

}
