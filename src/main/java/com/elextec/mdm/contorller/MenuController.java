package com.elextec.mdm.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.service.IMdmModelService;
import com.elextec.mdm.service.IMenuService;

@RestController
@RequestMapping("/mdm/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IMdmModelService mdmModelService;
	
	@GetMapping
	public Object getMenus(@RequestParam("level") String level) {
		VoResponse voResponse = new VoResponse();
		List<Menu> list = menuService.getMenus(level);
		List<Menu> listMenu = setMenu(list);
		voResponse.setData(listMenu);
		return voResponse;
	}
	
	private List<Menu> setMenu(List<Menu> list){
		for(Menu menu : list){
			menu.setLeaf(true);
			if(menu.getMenus().size() > 0 && menu.getLevel() < 1000){
				menu.setLeaf(false);
				menu.setMenus(setMenu(menu.getMenus()));
			}
		}
		return list;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id) {
		VoResponse voResponse = menuService.delMenu(id);
		return voResponse;
	}
	
	@PostMapping
	public Object add(@RequestBody Menu menu) {
		VoResponse voResponse = menuService.addMenu(menu);
		return voResponse;
	}
	
	@PutMapping
	public Object update(@RequestBody Menu menu) {
		VoResponse voResponse = menuService.updateMenu(menu);
		return voResponse;
	}
	
	
}
