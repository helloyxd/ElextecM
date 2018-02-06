package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.mapper.MenuMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IMenuService;
import com.elextec.mdm.vo.VoMenu;

@Service
public class MenuService extends BaseService implements IMenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getAllMenus() {
		List<Menu> list = menuMapper.findAll();
		return list;
	}
	
	@Override
	public List<VoMenu> getAllMenusTree() {
		List<Menu> list = menuMapper.findAllMenusTree();
		List<VoMenu> listMenu = setMenu(list);
		return listMenu;
	}
	
	private List<VoMenu> setMenu(List<Menu> list){
		List<VoMenu> listMenu = new ArrayList<VoMenu>();
		for(Menu menu : list){
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
			listMenu.add(e);
		}
		return listMenu;
	}
	
	@Override
	public List<VoMenu> getMenusTreeByRole() {
		String userId = getUserId();
		
		List<Menu> listMenu = menuMapper.findAllMenusTree();
		List<VoMenu> list = setMenu(listMenu);
		return list;
	}
	
	@Override
	public List<Menu> getMenus(String level) {
		List<Menu> list = menuMapper.findAllByLevel(level);
		return list;
	}
	
	@Override
	public VoResponse delMenu(String id) {
		VoResponse voRes = new VoResponse();
		Menu menu = menuMapper.findById(id);
		if(menu == null || menu.getMenus().size() > 0){
			voRes.setFail(voRes);
			return voRes;
		}
		menuMapper.delById(id);
		return voRes;
	}

	@Override
	public VoResponse addMenu(Menu menu) {
		VoResponse voRes = new VoResponse();
		if(menu.getMenuName() == null || menu.getMenuName().equals("")){
			voRes.setNull(voRes);
			voRes.setMessage("菜单名称不能为空");
			return voRes;
		}
		if(menu.getParentId() != null){
			Menu perMenu = menuMapper.findById(menu.getParentId());
			if(perMenu == null){
				voRes.setNull(voRes);
				voRes.setMessage("上级菜单获取异常");
				return voRes;
			}
			if(menu.getLevel() == null || menu.getLevel() != 1000){
				menu.setLevel(perMenu.getLevel() + 1);
			}
		}else{
			if(menuMapper.queryCount() == 0){
				menu.setParentId(initMenu());
				menu.setLevel(1);
			}else{
				menu.setParentId(getParentId());
				menu.setLevel(1);
			}
		}
		menu.setCreater(getUserName());
		menuMapper.insert(menu);
		return voRes;
	}
	
	private String getParentId(){
		String parentId = null;
		List<Menu> list = menuMapper.findByName("佳源集团主数据管理系统");
		if(list!=null && list.size()>0){
			parentId =  list.get(0).getId();
		}
		return parentId;
	}
	
	private String initMenu(){
		Menu superMenu = new Menu();
		superMenu.setMenuName("佳源集团主数据管理系统");
		superMenu.setCreater("sys");
		superMenu.setLevel(0);
		superMenu.setSortOrder(0);
		menuMapper.insert(superMenu);
		Menu menu1 = new Menu();
		menu1.setMenuName("主数据管理");
		menu1.setParentId(superMenu.getId());
		menu1.setCreater("sys");
		menu1.setLevel(1);
		menu1.setSortOrder(0);
		menuMapper.insert(menu1);
		Menu menu2 = new Menu();
		menu2.setMenuName("业务参数设置");
		menu2.setParentId(superMenu.getId());
		menu2.setCreater("sys");
		menu2.setLevel(1);
		menu2.setSortOrder(1);
		menuMapper.insert(menu2);
		Menu menu3 = new Menu();
		menu3.setMenuName("任务调度中心");
		menu3.setParentId(superMenu.getId());
		menu3.setCreater("sys");
		menu3.setLevel(1);
		menu3.setSortOrder(2);
		menuMapper.insert(menu3);
		return superMenu.getId();
	}

	@Override
	public VoResponse updateMenu(Menu menu) {
		VoResponse voRes = new VoResponse();
		Menu oldMenu = menuMapper.findById(menu.getId());
		if(oldMenu == null){
			voRes.setNull(voRes);
			return voRes;
		}
		/*if(oldMenu.getMenuName().equals("主数据管理") && oldMenu.getLevel() == 1){
			voRes.setFail(voRes);
			return voRes;
		}*/
		menuMapper.update(menu);
		return voRes;
	}

	@Override
	public boolean createMDMenu(MdmModel model, String tableName,
			String tableLabel) {
		List<Menu> listMenu = menuMapper.findByName("主数据管理");
		Menu MDMenu = null;
		for(Menu menu : listMenu){
			if(menu.getLevel() == 1){
				MDMenu = menu;
				break;
			}
		}
		if(MDMenu != null){
			List<Menu> subMenu = MDMenu.getMenus();
			String parentId = MDMenu.getId();
			MDMenu = null;
			for(Menu menu : subMenu){
				if(menu.getMenuName().equals(model.getMdmModel())){
					MDMenu = menu;
					break;
				}
			}
			subMenu = null;
			Menu menu = new Menu();
			if(MDMenu != null){//model 菜单已经存在
				subMenu = MDMenu.getMenus();
				if(subMenu.size() > 0){//存在子菜单
					//创建model下的 三级菜单
					menu.setParentId(MDMenu.getParentId());
					menu.setMenuName(tableLabel);
					menu.setMenuUrl("/mdm/table/defined/"+tableName);
					menu.setSortOrder(0);
					menu.setMethod("get");
					menu.setLevel(3);
					menu.setCreater(getUserName());
					menuMapper.insert(menu);
				}else{
					
				}
			}else{
				//创建model 二级菜单
				menu.setParentId(parentId);
				menu.setMenuName(model.getMdmModel());
				menu.setMenuUrl("/mdm/table/defined/");
				menu.setSortOrder(0);
				menu.setMethod("get");
				menu.setLevel(2);
				menu.setCreater(getUserName());
				menuMapper.insert(menu);
			}
			createMDFunctionMenu(menu);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean dropMDMenu(TableDefinition table){
		String name = table.getModel().getMdmModel();
		List<Menu> listMenu = menuMapper.findByName("主数据管理");
		Menu mdmenu = null;
		for(Menu menu : listMenu){
			for(Menu e : menu.getMenus()){
				if(e.getMenuName().equals(name)){
					mdmenu = e;
					break;
				}
			}
		}
		if(mdmenu != null){
			del(mdmenu);
			return true;
		}
		return false;
	}
	
	private void del(Menu e){
		menuMapper.delById(e.getId());
		for(Menu submenu : e.getMenus()){
			del(submenu);
		}
	}
	
	private void createMDFunctionMenu(Menu menu){
		String [] ss = {"增,post","删,delete","改,put","查,get"};
		for(int i = 0; i<ss.length; i++){
			Menu e = new Menu();
			String[] s = ss[i].split(",");
			e.setMenuName(s[0]);
			e.setParentId(menu.getId());
			e.setCreater(getUserName());
			e.setLevel(1000);
			e.setMenuUrl(menu.getMenuUrl());
			e.setMethod(s[1]);
			e.setSortOrder(i);
			menuMapper.insert(e);
		}
	}
	

}
