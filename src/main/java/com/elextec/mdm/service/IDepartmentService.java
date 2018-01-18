package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Department;

public interface IDepartmentService{

	public List<Department> getAllDepartments();
	
	public VoResponse delDepartment(String id);
	
	public VoResponse addDepartment(Department department);
	
	public VoResponse updateDepartment(Department department);
}
