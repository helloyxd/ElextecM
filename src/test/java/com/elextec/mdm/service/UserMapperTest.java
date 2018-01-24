package com.elextec.mdm.service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.constant.StatusEnum;
import com.elextec.mdm.common.entity.constant.UserStatusEnum;
import com.elextec.mdm.entity.Department;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.mapper.DepartmentMapper;
import com.elextec.mdm.mapper.MenuMapper;
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
	private MenuMapper menuMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
    @Test
    public void getUserByName() throws Exception {
    	List<User> list = userMapper.findUserByName("admin0");
    	//System.out.println("===========" + list.size());
    	ObjectMapper mapper = new ObjectMapper();
    	//mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void getAllUsers() throws Exception {
    	List<User> list = userMapper.findAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void getUserById() throws Exception {
    	User user = userMapper.findUserById("798D5F70C6434815A1A3194C48695EC4");
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(user));
    }
    
    @Test
    public void addUser() throws Exception {
    	/*for(int i=0;i<100;i++){
    		User user = new User();
        	user.setUserName("admin" + i);
        	user.setUserPassword("123");
        	//user.setFullName("auto add");
        	user.setStatus(UserStatusEnum.UserStatusNormal);
        	user.setCreater("SYS");
        	userMapper.insert(user);
    	}*/
    	
    	User user = new User();
    	user.setUserName("XXX");
    	user.setUserPassword("123");
    	//user.setFullName("auto add");
    	Department department = new Department();
    	department.setId("1111");
    	//user.setDepartment(department);
    	user.setStatus(UserStatusEnum.UserStatusNormal);
    	user.setCreater("SYS");
    	userMapper.insert(user);
    }
    
    @Test
    public void updateUserinfo() throws Exception {
    	User user = userMapper.findUserById("798D5F70C6434815A1A3194C48695EC4");
    	user.setUserPassword("zkj123");
    	user.setFullName("zhangkaijun");
    	user.setStatus(UserStatusEnum.UserStatusLock);
    	userMapper.update(user);
    	user = userMapper.findUserById("798D5F70C6434815A1A3194C48695EC4");
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(user));
    }
    
    @Test
    public void delUser()throws Exception {
    	userMapper.delete("4C6400628A60464EB227F8A46C2C1FF8");
    	User user = userMapper.findUserById("4C6400628A60464EB227F8A46C2C1FF8");
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(user));
    }
    
    @Test
    public void addUserRole() throws Exception {
    	User user = new User();
    	user.setId("哈哈user2");
    	List<Role> roles = new ArrayList<Role>();
    	Role role = new Role();
    	role.setId("哈哈role1");
    	roles.add(role);
    	Role role2 = new Role();
    	role2.setId("哈哈role2");
    	roles.add(role2);
    	user.setRoles(roles);
    	userMapper.addUserRoles(user);
    }
    
    @Test
    public void delUserRole() throws Exception {
    	User user = new User();
    	//user.setId(7);
    	//userMapper.delUserRoles(user.getId());
    }
    
    @Test
    public void addRole() throws Exception {
    	Role role = new Role();
    	role.setRoleName("admin2");
    	role.setRoleDesc("admin2");
    	role.setStatus(0);
    	role.setCreater("sys");
    	roleMapper.insert(role);
    	
    }
    
    @Test
    public void getAllRole() throws Exception {
    	List<Role> list = roleMapper.findAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void addDepartment() throws Exception {
    	Department department = new Department();
    	department.setDepartCode("30001");
    	department.setDepartName("IC-4");
    	department.setStatus(StatusEnum.StatusEnable);
    	department.setParentId("");
    	/*List<Department> nextDepartments = new ArrayList<Department>();
    	Department nextDepartment = new Department();
    	nextDepartment.setId(2);
    	nextDepartments.add(nextDepartment);*/
    	departmentMapper.insert(department);
    }
    
    @Test
    public void findAllDepartments() throws JsonProcessingException{
    	List<Department> departments = departmentMapper.findAll();
    	VoResponse vo = new VoResponse();
    	vo.setData(departments);
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(departments));
    	System.out.println(mapper.writeValueAsString(vo));
    }
    
    @Test
    public void findDepartmentById() throws JsonProcessingException{
    	Department department = departmentMapper.findDepartmentById("");
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(department));
    }
    
    @Test
    public void addMenu() throws Exception {
    	Menu menu = new Menu();
    	menu.setMenuName("3 level");
    	menu.setLevel(1);
    	menu.setMenuUrl("menu");
    	menu.setRemark("desc");
    	menu.setParentId("");
    	menu.setSortOrder(1);
    	menu.setStatus(UserStatusEnum.UserStatusNormal);
    	menu.setCreater("zkj");
    	menuMapper.insert(menu);
    }
    
    @Test
    public void delMenu() throws Exception {
    	menuMapper.delById("1");
    }
    
    @Test
    public void getAllMenus() throws Exception {
    	List<Menu> list = menuMapper.findAll();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void getAllMenus1Level() throws Exception {
    	List<Menu> list = menuMapper.findSuperMenus();
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(list));
    }
    
    @Test
    public void addRoleMenus() throws Exception {
    	Role role = new Role();
    	//role.setId(1);
    	List<Menu> menus = new ArrayList<Menu>();
    	Menu menu1 = new Menu();
    	//menu1.setId(2);
    	menus.add(menu1);
    	Menu menu2 = new Menu();
    	//menu2.setId(3);
    	menus.add(menu2);
    	role.setMenus(menus);
    	roleMapper.addRoleMenus(role);
    }
    
    @Test
    public void getRoleById() throws Exception {
    	Role role = roleMapper.findRoleById("");
    	ObjectMapper mapper = new ObjectMapper();
    	System.out.println(mapper.writeValueAsString(role));
    }
    
    @Test
    public void getUserPage() throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	User user = new User();
    	user.setUserName("%admin%");
    	int count = userMapper.findCount(user);
    	System.out.println(count);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setAllCount(count);
		pageQuery.setCurrentPage(10);
		pageQuery.setPageRowSize(10);
		pageQuery.setOrder("user_name");
		pageQuery.calcutePage(pageQuery);
		System.out.println(mapper.writeValueAsString(pageQuery));
		List<User> list = userMapper.findUserByPage(user, pageQuery);
    	System.out.println(mapper.writeValueAsString(list));
    }
    
  
        private static final QName SERVICE_NAME = new QName("http://webservice.mdm.elextec.com/", "createMpc");
        /**
         * 
         * @throws Exception
         */
        public static void main(String[] args) throws Exception {
            // 远程webService的URL
            String hostUrl = "http://localhost:8080/mdm/services/user?wsdl";
            try {
                // 创建动态客户端
                JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
                // 创建客户端连接
                Client client = factory.createClient(hostUrl, SERVICE_NAME);
                ClientImpl clientImpl = (ClientImpl) client;
                Endpoint endpoint = clientImpl.getEndpoint();
                // Make use of CXF service model to introspect the existing WSDL
                ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
                // 创建QName来指定NameSpace和要调用的service
                QName bindingName = new QName("http://webservice.mdm.elextec.com/", "createMpc");
                BindingInfo binding = serviceInfo.getBinding(bindingName);
                // 创建QName来指定NameSpace和要调用的方法
               // QName bindingName = new QName("http://webservice.mdm.elextec.com/", "createMpc");
                 
                BindingOperationInfo boi = binding.getOperation(bindingName);
                BindingMessageInfo inputMessageInfo = boi.getInput();
                List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
                // 取得对象实例
                MessagePartInfo partInfo = parts.get(0);
                Class<?> partClass = partInfo.getTypeClass();
                Object inputObject = partClass.newInstance();
     
                // 取得字段的set方法并赋值
                PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor("userName", partClass);
                Method userNoSetter = partPropertyDescriptor.getWriteMethod();
                userNoSetter.invoke(inputObject, "yu");
     
                // 取得字段的set方法并赋值
                //PropertyDescriptor partPropertyDescriptor2 = new PropertyDescriptor(字段名, partClass);
                //Method productCodeSetter = partPropertyDescriptor2.getWriteMethod();
               // productCodeSetter.invoke(inputObject, 属性值);
     
                // 调用客户端invoke()方法，把inputObject传递给要调用的方法并取得结果对象
                Object[] result = client.invoke(bindingName, inputObject);
                // 取得的结果是一个对象
                Class<?> resultClass = result[0].getClass();
                // 取得返回结果的get方法并得到它的值
                PropertyDescriptor resultDescriptor = new PropertyDescriptor("userName", resultClass);
                Object resultGetter = resultDescriptor.getReadMethod().invoke(result[0]);
                System.out.println("result：" + resultGetter);
                // 取得返回结果的get方法并得到它的值
              //  PropertyDescriptor tokenDescriptor = new PropertyDescriptor("userName", resultClass);
                // 取得的是一个对象实例
               // Object getObj= tokenDescriptor.getReadMethod().invoke(result[0]);
               // if(tokenGetter != null) {
                  //  Class<?> resultTokenClass = tokenDescriptor.getReadMethod().invoke(result[0]).getClass();
                    // 得到对象实例下的***属性值
                   // PropertyDescriptor expiredTimeDescriptor = new PropertyDescriptor(字段名, resultTokenClass);
                   // Object getter = expiredTimeDescriptor.getReadMethod().invoke(getObj);
                    //System.out.println("字段名：" + getter );
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
}
