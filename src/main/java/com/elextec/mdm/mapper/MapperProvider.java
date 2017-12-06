package com.elextec.mdm.mapper;

import java.text.MessageFormat;
import java.util.Map;

import com.elextec.mdm.entity.User;

public class MapperProvider {

	public String addUserRoles(Map<String, User> map){
		User user = map.get("user");
		StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO user_role(user_id,role_id) VALUES");
        MessageFormat mf = new MessageFormat("(#'{'user.id}, #'{'user.roles[{0}].id})");
        for(int i = 0 ;i<user.getRoles().size();i++) {
        	sb.append(mf.format(new Object[]{i}));  
            if (i < user.getRoles().size() - 1) {  
                sb.append(",");  
            }  
        }
        System.out.println(sb);
		return sb.toString();
	}
	
	
}
