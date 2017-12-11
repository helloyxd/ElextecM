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
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.service.IRoleService;

@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/getAll")
	public Object getAllDepartments() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(roleService.getAllRoles());
		return voResponse;
	}
	
	@DeleteMapping(value="{id}")
	public Object del(@PathVariable("id") int id) {
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
}
