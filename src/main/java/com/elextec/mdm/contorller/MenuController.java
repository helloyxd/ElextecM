package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.service.IMenuService;

@RestController
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@GetMapping("/getAll")
	public Object getAllMenus() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(menuService.getAllMenus());
		return voResponse;
	}
	
	@DeleteMapping(value="{id}")
	public Object del(@PathVariable("id") String id) {
		VoResponse voResponse = menuService.delMenu(id);
		return voResponse;
	}
	
	@PostMapping
	public Object add(@RequestBody Menu menu) {
		menu.setCreater("sys");
		VoResponse voResponse = menuService.addMenu(menu);
		return voResponse;
	}
	
	@PutMapping
	public Object update(@RequestBody Menu menu) {
		VoResponse voResponse = menuService.updateMenu(menu);
		return voResponse;
	}
}
