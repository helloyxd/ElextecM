package com.elextec.bi.controller;

import com.elextec.bi.service.IBiMenuService;
import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiMenu;
import com.elextec.mdm.service.IMdmModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bi/menu")
public class BiMenuController {

	@Autowired
	private IBiMenuService biMenuService;
	
//	@Autowired
//	private IMdmModelService mdmModelService;
	
	@GetMapping("/getAll")
	public Object getAllMenus() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(biMenuService.getAllMenus());
		return voResponse;
	}
	
	@GetMapping("/getTree")
	public Object getAllMenusTree() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(biMenuService.getAllMenusTree());
		return voResponse;
	}
	
	@GetMapping
	public Object getMenus(@RequestParam("level") String level) {
		VoResponse voResponse = new VoResponse();
		List<BiMenu> list = biMenuService.getMenus(level);
		voResponse.setData(list);
		return voResponse;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id) {
		VoResponse voResponse = biMenuService.delMenu(id);
		return voResponse;
	}
	
	@PostMapping
	public Object add(@RequestBody BiMenu menu) {
		VoResponse voResponse = biMenuService.addMenu(menu);
		return voResponse;
	}
	
	@PutMapping
	public Object update(@RequestBody BiMenu menu) {
		VoResponse voResponse = biMenuService.updateMenu(menu);
		return voResponse;
	}
	
	
}
