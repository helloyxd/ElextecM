package com.elextec.mdm.service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Menu;

public interface IMenuService {

	Object getAllMenus();

	VoResponse delMenu(int id);

	VoResponse addMenu(Menu menu);

	VoResponse updateMenu(Menu menu);

	
}
