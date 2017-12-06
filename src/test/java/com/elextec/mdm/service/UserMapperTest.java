package com.elextec.mdm.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.common.entity.StatusEnum;
import com.elextec.mdm.common.entity.UserStatusEnum;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.Department;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.DepartmentMapper;
import com.elextec.mdm.mapper.RoleMapper;
import com.elextec.mdm.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
    	List<User> list = userMapper.findUserByName("admin3");
    	//System.out.println("===========" + list.size());
    	ObjectMapper mapper = new ObjectMapper();
    	//mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void getAllUsers() throws Exception {
    	List<User> list = userMapper.getAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void getUserById() throws Exception {
    	User user = userMapper.findUserById(1);
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(user));
    }
    
    @Test
    public void addUser() throws Exception {
    	User user = new User();
    	user.setUserName("admin3");
    	user.setUserPassword("123");
    	user.setStatus(UserStatusEnum.UserStatusNormal);
    	Department department = new Department();
    	department.setId(2);
    	user.setDepartment(department);
    	List<Role> roles = new ArrayList<Role>();
    	Role role = new Role();
    	role.setId(2);
    	roles.add(role);
    	user.setRoles(roles);
    	userMapper.insert(user);
    }
    
    @Test
    public void updateUserinfo() throws Exception {
    	User user = userMapper.findUserById(1);
    	user.setUserPassword("zkj123");
    	user.setFullName("zhangkaijun");
    	user.setStatus(UserStatusEnum.UserStatusLock);
    	userMapper.update(user);
    	user = userMapper.findUserById(1);
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(user));
    }
    
    @Test
    public void addUserRole() throws Exception {
    	User user = new User();
    	user.setId(7);
    	List<Role> roles = new ArrayList<Role>();
    	Role role = new Role();
    	role.setId(2);
    	roles.add(role);
    	Role role1 = new Role();
    	role1.setId(3);
    	roles.add(role1);
    	user.setRoles(roles);
    	userMapper.addUserRoles(user);
    }
    
    @Test
    public void delUserRole() throws Exception {
    	User user = new User();
    	user.setId(7);
    	userMapper.delUserRoles(user.getId());
    }
    
    @Test
    public void addRole() throws Exception {
    	Role role = new Role();
    	role.setRoleName("admin3");
    	role.setRoleDesc("admin3");
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
    	department.setDepartCode("30001");
    	department.setDepartName("IC-4");
    	department.setStatus(StatusEnum.StatusEnable);
    	department.setParentId(7);
    	/*List<Department> nextDepartments = new ArrayList<Department>();
    	Department nextDepartment = new Department();
    	nextDepartment.setId(2);
    	nextDepartments.add(nextDepartment);*/
    	departmentMapper.insert(department);
    }
    
    @Test
    public void findAllDepartments() throws JsonProcessingException{
    	List<Department> departments = departmentMapper.getAll();
    	VoResponse vo = new VoResponse();
    	vo.setData(departments);
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(departments));
    	System.out.println(mapper.writeValueAsString(vo));
    }
    
    @Test
    public void findDepartmentById() throws JsonProcessingException{
    	Department department = departmentMapper.findDepartmentById(2);
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(department));
    }
}
