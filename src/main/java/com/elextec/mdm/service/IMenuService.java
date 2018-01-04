package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.vo.VoMenu;

public interface IMenuService {

	List<Menu> getAllMenus();
	
	List<VoMenu> getAllMenusTree();
	
	List<Menu> getMenus(String level);

	VoResponse delMenu(String id);

	VoResponse addMenu(Menu menu);

	VoResponse updateMenu(Menu menu);

	
}
