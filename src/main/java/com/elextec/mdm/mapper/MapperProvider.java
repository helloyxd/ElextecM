package com.elextec.mdm.mapper;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;

public class MapperProvider {

	public String addUserRoles(Map<String, User> map){
		User user = map.get("user");
		StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO mdm_user_role(id,user_id,role_id) "
        		+ "VALUES(sys_guid(), #'{'user.id}, #'{'user.roles[{0}].id}) ");
        for(int i = 0; i < user.getRoles().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addRoleMenus(Map<String, Role> map){
		Role role = map.get("role");
		StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO mdm_role_menu(role_id,menu_id) VALUES");
        MessageFormat mf = new MessageFormat("(#'{'role.id}, #'{'role.menus[{0}].id})");
        for(int i = 0; i < role.getMenus().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
            if (i < role.getMenus().size() - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb);
		return sb.toString();
	}
	
	public String findUserByPage(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		User user = (User) map.get("user");
		PageQuery pageQuery = (PageQuery) map.get("page");
		//sb.append("SELECT * FROM mdm_user WHERE 1=1");
		sb.append("SELECT * FROM (SELECT ROWNUM AS rn, t.* FROM mdm_user t WHERE 1=1");
		sb.append(getUserCondition(user));
		sb.append(" AND ROWNUM <= ").append(pageQuery.getPageRowSize()*pageQuery.getCurrentPage()).append(") tt WHERE tt.rn > ");
		sb.append(pageQuery.getBeginIndex());
		/*if(pageQuery.getOrder() != null)
			sb.append(" ORDER BY ").append(pageQuery.getOrder());
		sb.append(" LIMIT ").append(pageQuery.getBeginIndex()).append(",").append(pageQuery.getPageRowSize());*/
		System.out.println(sb);
		return sb.toString();
	}
	
	public String findUserCount(Map<String, User> map){
		StringBuilder sb = new StringBuilder();
		User user = map.get("user");
		sb.append("SELECT COUNT(*) FROM mdm_user WHERE 1=1");
		sb.append(getUserCondition(user));
		System.out.println(sb);
		return sb.toString();
	}
	
	public String getUserCondition(User user){
		StringBuilder sb = new StringBuilder();
		if(user.getUserName() != null)
			sb.append(" AND user_name LIKE '").append(user.getUserName()).append("'");
		if(user.getFullName() != null)
			sb.append(" AND full_name LIKE '").append(user.getUserName()).append("'");
		return sb.toString();
	}
	
	
	public String test(Map<String, Role> map){
		StringBuilder sb = new StringBuilder();
		
		System.out.println(sb);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setUserName("%admin%");
		PageQuery pageQuery = new PageQuery();
		pageQuery.setOrder("user_name");
		map.put("user", user);
		map.put("page", pageQuery);
		new MapperProvider().findUserByPage(map);
		
	}
	
}
