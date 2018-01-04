package com.elextec.bi.service;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiMenu;
import com.elextec.bi.vo.BiVoMenu;

import java.util.List;

public interface IBiMenuService {

	List<BiMenu> getAllMenus();
	
	List<BiVoMenu> getAllMenusTree();
	
	List<BiMenu> getMenus(String level);

	VoResponse delMenu(String id);

	VoResponse addMenu(BiMenu menu);

	VoResponse updateMenu(BiMenu menu);

	
}
