package com.elextec.mdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.ResponseCodeEnum;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.entity.Department;
import com.elextec.mdm.mapper.DepartmentMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IDepartmentService;

@Service
public class DepartmentService extends BaseService implements IDepartmentService{

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getAllDepartments(){
		List<Department> list = departmentMapper.findSuperDepartments();
		return list;
	}

	@Override
	public VoResponse delDepartment(String id) {
		VoResponse vo = new VoResponse();
		Department department = departmentMapper.findAllDepartmentsById(id);
		if(department == null){
			vo.setCode(ResponseCodeEnum.CodeFail);
			vo.setMessage("delete failure,department does not exist");
			vo.setSuccess(Boolean.FALSE);
		}else if(department.getDepartments() != null && department.getDepartments().size() > 0){
			vo.setCode(ResponseCodeEnum.CodeFail);
			vo.setMessage("delete failure,Please delete the lower department first");
			vo.setSuccess(Boolean.FALSE);
			vo.setData(department.getDepartments());
		}else{
			departmentMapper.delById(id);
			vo.setMessage("delete department success");
		}
		return vo;
	}

	@Override
	public VoResponse addDepartment(Department department) {
		VoResponse vo = new VoResponse();
		List<Department> list = departmentMapper.findByCodeOrName(department.getDepartCode(), department.getDepartName());
		for(Department obj : list){
			if(obj.getDepartCode().equals(department.getDepartCode())){
				vo.setCode(ResponseCodeEnum.CodeFail);
				vo.setMessage("add failure,DepartCode is already exists");
				vo.setSuccess(Boolean.FALSE);
				return vo;
			}else if(obj.getDepartName().equals(department.getDepartName())){
				vo.setCode(ResponseCodeEnum.CodeFail);
				vo.setMessage("add failure,DepartName is already exists");
				vo.setSuccess(Boolean.FALSE);
				return vo;
			}
		}
		department.setCreater(getUserName());
		department.setStatus(StatusEnum.StatusEnable);
		departmentMapper.insert(department);
		vo.setMessage("add department success");
		return vo;
	}

	@Override
	public VoResponse updateDepartment(Department department) {
		VoResponse vo = new VoResponse();
		Department old = departmentMapper.findAllDepartmentsById(department.getId());
		if(old != null){
			old.setDepartName(department.getDepartName());
			departmentMapper.update(old);
		}
		return vo;
	}

}
