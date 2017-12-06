package com.elextec.mdm.service;

import java.util.List;

import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.Department;
import com.elextec.mdm.entity.User;

public interface IUserService {
	public String getUserName();
	public VoResult registerUser(User user);
	public List<User> getAll();
	public List<Department> getAllDepartments();
}
