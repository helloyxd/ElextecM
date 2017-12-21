package com.elextec.mdm.contorller;

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
import com.elextec.mdm.entity.DataPermission;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.service.IRoleService;

@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/getAll")
	public Object getAllRoles() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(roleService.getAllRoles());
		return voResponse;
	}
	
	@GetMapping
	public Object getRole(@RequestParam("id") String roleId) {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(roleService.getRoleById(roleId));
		return voResponse;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id) {
		VoResponse voResponse = roleService.delRole(id);
		return voResponse;
	}
	
	@PostMapping
	public Object add(@RequestBody Role role) {
		VoResponse voResponse = roleService.addRole(role);
		return voResponse;
	}
	
	@PutMapping
	public Object update(@RequestBody Role role) {
		VoResponse voResponse = roleService.updateRole(role);
		return voResponse;
	}
	
	@PostMapping("addRoleMenus")
	public Object addRoleMenus(@RequestBody Role role) {
		VoResponse voResponse = roleService.updateRoleMenu(role);
		return voResponse;
	}
	
	@PostMapping("addDataPermission")
	public Object addRoleDataPermission(@RequestBody DataPermission dataPermission){
		VoResponse voResponse = roleService.addRoleDataPermission(dataPermission);
		return voResponse;
	}
	
	
}
