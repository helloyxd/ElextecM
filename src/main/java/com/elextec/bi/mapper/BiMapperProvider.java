package com.elextec.bi.mapper;

import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.entity.BiForeignFinancing;
import com.elextec.bi.entity.BiRole;
import com.elextec.bi.entity.BiUser;
import com.elextec.mdm.entity.DataPermissionDefined;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiMapperProvider {

	public String addUserRoles(Map<String, BiUser> map){
		BiUser user = map.get("user");
		StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO bi_user_role(id,user_id,role_id) "
        		+ "VALUES(sys_guid(), #'{'user.id}, #'{'user.roles[{0}].id}) ");
        for(int i = 0; i < user.getRoles().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addRoleMenus(Map<String, BiRole> map){
		BiRole role = map.get("role");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO bi_role_menu(id,role_id,menu_id,role_type) "
        		+ "VALUES(sys_guid(), #'{'role.id}, #'{'role.menus[{0}].id}, 0) ");
        for(int i = 0; i < role.getMenus().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addRoleDataPermission(Map<String, BiRole> map){
		BiRole role = map.get("role");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO bi_role_data(id,role_id,data_id,role_type) "
        		+ "VALUES(sys_guid(), #'{'role.id}, #'{'role.dataPermissions[{0}].id}, 0) ");
        for(int i = 0; i < role.getDataPermissions().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addDataPermissions(Map<String, DataPermissionDefined> map){
		DataPermissionDefined entity = map.get("dataPermissionDefined");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO bi_datapermission(id,defined_id,role_id,permission_value,create_time,creater,status) "
        		+ "VALUES(sys_guid(), #'{'dataPermissionDefined.id}, #'{'dataPermissionDefined.dataPermission[{0}].roleId}, "
        		+ "#'{'dataPermissionDefined.dataPermission[{0}].permissionValue}, sysdate, #'{'dataPermissionDefined.dataPermission[{0}].creater}, 0) ");
        for(int i = 0; i < entity.getDataPermission().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String findUserByPage(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		BiUser user = (BiUser) map.get("user");
		PageQuery pageQuery = (PageQuery) map.get("page");
		//sb.append("SELECT * FROM mdm_user WHERE 1=1");
		sb.append("SELECT * FROM (SELECT ROWNUM AS rn, t.* FROM bi_user t WHERE 1=1");
		sb.append(getUserCondition(user));
		sb.append(" AND ROWNUM <= ").append(pageQuery.getPageRowSize()*pageQuery.getCurrentPage()).append(") tt WHERE tt.rn > ");
		sb.append(pageQuery.getBeginIndex());
		/*if(pageQuery.getOrder() != null)
			sb.append(" ORDER BY ").append(pageQuery.getOrder());
		sb.append(" LIMIT ").append(pageQuery.getBeginIndex()).append(",").append(pageQuery.getPageRowSize());*/
		System.out.println(sb);
		return sb.toString();
	}

	public String findForeignFinancingByPage(Map<String,Object> map){
		StringBuilder sb = new StringBuilder();
		String objName = (String) map.get("objName");
		PageQuery pageQuery = (PageQuery) map.get("page");
		sb.append("SELECT * FROM (\n" +
				"        SELECT u.*,ROWNUM RN\n" +
				"        FROM (select j.*,o.\"org_name\" AS ORG_NAME\n" +
				"        FROM BI_FOREIGN_FINANCING j\n" +
				"        LEFT JOIN (select \"org_id\",\"org_name\" FROM BI_ORG_STAGES\n" +
				"        GROUP BY \"org_id\",\"org_name\") o ON j.ORG_ID = o.\"org_id\") u WHERE ROWNUM <= ");
		sb.append(pageQuery.getPageRowSize()*pageQuery.getCurrentPage());
		sb.append(" ORDER BY u.create_time DESC)");
		sb.append(" where RN >= ");
		sb.append((pageQuery.getCurrentPage()-1)*pageQuery.getPageRowSize()+1);
		if(objName != null){
			sb.append(" AND (org_name like '%");
			sb.append(objName);
			sb.append("%' OR region_com_name like '%");
			sb.append(objName);
			sb.append("%')");
		}
		sb.append(" ORDER BY create_time DESC");
		System.out.println(sb);
		return sb.toString();
	}

	public String findForeignFinancingCount(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		String objName = (String) map.get("objName");

		sb.append("SELECT count(1)\n" +
				"        FROM BI_FOREIGN_FINANCING u\n" +
				"        LEFT JOIN (select \"org_id\",\"org_name\" FROM JYBI_RPT_ORG_STAGES GROUP BY \"org_id\",\"org_name\") o ON u.ORG_ID = o.\"org_id\"");
		if(objName != null){
			sb.append(" where (o.\"org_name\" like '%");
			sb.append(objName);
			sb.append("%' OR u.region_com_name like '%");
			sb.append(objName);
			sb.append("%')");
		}
		System.out.println(sb);
		return sb.toString();
	}
	
	public String findUserCount(Map<String, BiUser> map){
		StringBuilder sb = new StringBuilder();
		BiUser user = map.get("user");
		sb.append("SELECT COUNT(*) FROM bi_user WHERE 1=1");
		sb.append(getUserCondition(user));
		System.out.println(sb);
		return sb.toString();
	}
	
	public String getUserCondition(BiUser user){
		StringBuilder sb = new StringBuilder();
		if(user.getUserName() != null)
			sb.append(" AND user_name LIKE '").append(user.getUserName()).append("'");
		if(user.getFullName() != null)
			sb.append(" AND full_name LIKE '").append(user.getUserName()).append("'");
		return sb.toString();
	}
	
	public String delAll(Map<String, Object> map) throws Exception{
		StringBuilder sb = new StringBuilder();
		String tableName = (String) map.get("tableName");
		@SuppressWarnings("unchecked")
		List<String> ids = (List<String>) map.get("ids");
		sb.append("DELETE FROM ").append(tableName).append(" WHERE id in(");
		for(String id : ids){
			sb.append("'").append(id).append("',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		System.out.println(sb);
		return sb.toString();
	}
	
	public String test(Map<String, BiRole> map){
		StringBuilder sb = new StringBuilder();
		
		System.out.println(sb);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
//		BiUser user = new BiUser();
//		user.setUserName("%admin%");
		PageQuery pageQuery = new PageQuery();

//		pageQuery.setOrder("user_name");
//		map.put("user", user);
//		map.put("page", pageQuery);
//		new BiMapperProvider().findUserByPage(map);

		pageQuery.setCurrentPage(1);
		pageQuery.setPageRowSize(10);
		map.put("objName","123");
//		map.put("page",pageQuery);
		new BiMapperProvider().findForeignFinancingCount(map);


		
	}
	
}
