package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.MdmModel;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.TableDefinition;
import com.elextec.mdm.vo.VoMenu;

public interface IMenuService {

	List<Menu> getAllMenus();
	
	List<VoMenu> getAllMenusTree();
	
	List<VoMenu> getMenusTreeByRole();
	
	List<Menu> getMenus(String level);

	VoResponse delMenu(String id);

	VoResponse addMenu(Menu menu);

	VoResponse updateMenu(Menu menu);

	boolean createMDMenu(MdmModel model, String tableName, String tableLabel);
	
	boolean dropMDMenu(TableDefinition table);
}
