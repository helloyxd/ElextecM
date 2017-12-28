package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Menu;

public interface IMenuService {

	List<Menu> getAllMenus();
	
	List<Menu> getAllMenusTree();
	
	List<Menu> getMenus(String level);

	VoResponse delMenu(String id);

	VoResponse addMenu(Menu menu);

	VoResponse updateMenu(Menu menu);

	
}
