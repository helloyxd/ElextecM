package com.elextec.bi.controller;

import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.entity.BiRole;
import com.elextec.bi.service.IBiRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bi/role")
public class BiRoleController {

	@Autowired
	private IBiRoleService biRoleService;
	
	@GetMapping("/getAll")
	public Object getAllRoles() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(biRoleService.getAllRoles());
		return voResponse;
	}
	
	@GetMapping
	public Object getRole(@RequestParam("id") String roleId) {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(biRoleService.getRoleById(roleId));
		return voResponse;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id) {
		VoResponse voResponse = biRoleService.delRole(id);
		return voResponse;
	}
	
	@PostMapping
	public Object add(@RequestBody BiRole role) {
		VoResponse voResponse = biRoleService.addRole(role);
		return voResponse;
	}
	
	@PutMapping
	public Object update(@RequestBody BiRole role) {
		VoResponse voResponse = biRoleService.updateRole(role);
		return voResponse;
	}
	
	@PostMapping("addRoleMenus")
	public Object addRoleMenus(@RequestBody BiRole role) {
		VoResponse voResponse = biRoleService.updateRoleMenu(role);
		return voResponse;
	}
	
//	@PostMapping("addDataPermission")
//	public Object addRoleDataPermission(@RequestBody DataPermission dataPermission){
//		VoResponse voResponse = roleService.addRoleDataPermission(dataPermission);
//		return voResponse;
//	}
	
	
}
