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
import com.elextec.mdm.entity.Department;
import com.elextec.mdm.service.IDepartmentService;

@RestController
@RequestMapping("/mdm/department")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;
	
	@GetMapping("/getAll")
	public Object getAllDepartments() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(departmentService.getAllDepartments());
		return voResponse;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String id) {
		VoResponse voResponse = departmentService.delDepartment(id);
		return voResponse;
	}
	
	@PostMapping
	public Object add(@RequestBody Department department) {
		VoResponse voResponse = departmentService.addDepartment(department);
		return voResponse;
	}
	
	@PutMapping
	public Object update(@RequestBody Department department) {
		VoResponse voResponse = new VoResponse();
		return voResponse;
	}
	
}
