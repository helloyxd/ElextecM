package com.elextec.mdm.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.common.entity.StatusEnum;
import com.elextec.mdm.entity.Department;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.DepartmentMapper;
import com.elextec.mdm.mapper.RoleMapper;
import com.elextec.mdm.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
    @Test
    public void getUserByName() throws Exception {
    	List<User> list = userMapper.findUserByName("zkj");
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void getAllUsers() throws Exception {
    	List<User> list = userMapper.getAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void addRole() throws Exception {
    	Role role = new Role();
    	role.setRoleName("admin2");
    	role.setRoleDesc("admin2");
    	roleMapper.insert(role);
    	
    }
    
    @Test
    public void getAllRole() throws Exception {
    	List<Role> list = roleMapper.getAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void addDepartment() throws Exception {
    	Department department = new Department();
    	department.setDepartCode("10002");
    	department.setDepartName("op");
    	department.setStatus(StatusEnum.StatusEnable);
    	departmentMapper.insert(department);
    }
    
    @Test
    public void findAllDepartments() throws JsonProcessingException{
    	List<Department> departments = departmentMapper.getAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(departments));
    }
    
    @Test
    public void findDepartmentById() throws JsonProcessingException{
    	Department department = departmentMapper.findDepartmentById(1);
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(department));
    }
}
