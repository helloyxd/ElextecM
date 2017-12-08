package com.elextec.mdm.mapper;

import java.text.MessageFormat;
import java.util.Map;

import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;

public class MapperProvider {

	public String addUserRoles(Map<String, User> map){
		User user = map.get("user");
		StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO user_role(user_id,role_id) VALUES");
        MessageFormat mf = new MessageFormat("(#'{'user.id}, #'{'user.roles[{0}].id})");
        for(int i = 0; i < user.getRoles().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));  
            if (i < user.getRoles().size() - 1) {  
                sb.append(",");  
            }  
        }
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addRoleMenus(Map<String, Role> map){
		Role role = map.get("role");
		StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO role_menu(role_id,menu_id) VALUES");
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
	
	
}
