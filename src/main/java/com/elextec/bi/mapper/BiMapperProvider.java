package com.elextec.bi.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.entity.BiForeignFinancing;
import com.elextec.bi.entity.BiRole;
import com.elextec.bi.entity.BiUser;
import com.elextec.mdm.entity.DataPermissionDefined;
import com.elextec.mdm.utils.StringUtil;
import com.mysql.cj.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiMapperProvider {

	public String addUserRoles(Map<String, Object> map){
//		BiUser user = map.get("user");
		String[] roles = (String[])map.get("roles");
		StringBuilder sb = new StringBuilder();
        sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO bi_user_role(id,user_id,role_id) "
        		+ "VALUES(sys_guid(), #'{'userId}, #'{'roles[{0}]}) ");
        for(int i = 0; i < roles.length; i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
//        System.out.println(sb);
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
//        System.out.println(sb);
		return sb.toString();
	}

	public String addReportDataPermission(Map<String, Object> map){
		String[] dataPermissions = (String[])map.get("dataPermissions");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ALL ");
		MessageFormat mf = new MessageFormat("INTO BI_REPORT_DATAPERMISSION(id,REPORT_ID,DATAPERMISSION_ID) "
				+ "VALUES(sys_guid(), #'{'reportId}, #'{'dataPermissions[{0}]}) ");
		for(int i = 0; i < dataPermissions.length; i++) {
			sb.append(mf.format(new Object[]{i}));
		}
		sb.append("SELECT 1 FROM DUAL");
//        System.out.println(sb);
		return sb.toString();
	}
	
//	public String addRoleDataPermission(Map<String, BiRole> map){
//		BiRole role = map.get("role");
//		StringBuilder sb = new StringBuilder();
//		sb.append("INSERT ALL ");
//        MessageFormat mf = new MessageFormat("INTO bi_role_data(id,role_id,data_id,role_type) "
//        		+ "VALUES(sys_guid(), #'{'role.id}, #'{'role.dataPermissions[{0}].id}, 0) ");
//        for(int i = 0; i < role.getDataPermissions().size(); i++) {
//        	sb.append(mf.format(new Object[]{i}));
//        }
//        sb.append("SELECT 1 FROM DUAL");
////        System.out.println(sb);
//		return sb.toString();
//	}
	
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
//        System.out.println(sb);
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
//		System.out.println(sb);
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
		if(objName != null && !com.mysql.cj.util.StringUtils.isNullOrEmpty(objName)){
			sb.append(" AND (org_name like '%");
			sb.append(objName);
			sb.append("%' OR region_com_name like '%");
			sb.append(objName);
			sb.append("%')");
		}
		sb.append(" ORDER BY create_time DESC");
//		System.out.println(sb);
		return sb.toString();
	}

	public String findForeignFinancingCount(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		String objName = (String) map.get("objName");

		sb.append("SELECT count(1)\n" +
				"        FROM BI_FOREIGN_FINANCING u\n" +
				"        LEFT JOIN (select \"org_id\",\"org_name\" FROM BI_ORG_STAGES GROUP BY \"org_id\",\"org_name\") o ON u.ORG_ID = o.\"org_id\"");
		if(objName != null && !StringUtils.isNullOrEmpty(objName)){
			sb.append(" where (o.\"org_name\" like '%");
			sb.append(objName);
			sb.append("%' OR u.region_com_name like '%");
			sb.append(objName);
			sb.append("%')");
		}
//		System.out.println(sb);
		return sb.toString();
	}
	
	public String findUserCount(Map<String, BiUser> map){
		StringBuilder sb = new StringBuilder();
		BiUser user = map.get("user");
		sb.append("SELECT COUNT(*) FROM bi_user WHERE 1=1");
		sb.append(getUserCondition(user));
//		System.out.println(sb);
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
		String test = "[{\n" +
				"\t\"dataPermissionId\": \"1\",\n" +
				"\t\"column\": \"orgName,comName,stageName\",\n" +
				"\t\"orgName\": \"申城集团，杭城集团\",\n" +
				"\t\"comName\": \"XXX,XXX,XXX\",\n" +
				"\t\"stageName\": \"XXX,XXX,XXX\"\n" +
				"}, {\n" +
				"\t\"dataPermissionId\": \"1\",\n" +
				"\t\"column\": \"orgName,comName,stageName\",\n" +
				"\t\"orgName\": \"申城集团，杭城集团\",\n" +
				"\t\"comName\": \"XXX,XXX,XXX\",\n" +
				"\t\"stageName\": \"XXX,XXX,XXX\"\n" +
				"}]";


		List<Map<String, Object>> mapList = JSON.parseObject(test, new TypeReference<List<Map<String, Object>>>() {
		});

		Map<String,Object> sqlMap = new HashMap<String,Object>();
		for(int i=0;i<mapList.size();i++){
			if(sqlMap.get(mapList.get(i).get("dataPermissionId")) != null){
				continue;
			}else{
				List<Map<String, Object>> temp = new ArrayList<Map<String, Object>>();
				for(int j=0;j<mapList.size();j++){
					if(mapList.get(i).get("dataPermissionId").equals(mapList.get(j).get("dataPermissionId"))){
						temp.add(mapList.get(j));
					}
				}
				sqlMap.put(mapList.get(i).get("dataPermissionId").toString(),temp);
			}
		}
        System.out.println(0);
	}
	
}
