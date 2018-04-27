package com.elextec.mdm.mapper;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elextec.mdm.common.entity.PageQuery;
import com.elextec.mdm.entity.DataPermissionDefined;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.TaskDataRecordSummary;
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
		sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO mdm_role_menu(id,role_id,menu_id,role_type) "
        		+ "VALUES(sys_guid(), #'{'role.id}, #'{'role.menus[{0}].id}, 0) ");
        for(int i = 0; i < role.getMenus().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addRoleDataPermission(Map<String, Role> map){
		Role role = map.get("role");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO mdm_role_data(id,role_id,data_id,role_type) "
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
        MessageFormat mf = new MessageFormat("INTO mdm_datapermission(id,defined_id,role_id,permission_value,create_time,creater,status) "
        		+ "VALUES(sys_guid(), #'{'dataPermissionDefined.id}, #'{'dataPermissionDefined.dataPermission[{0}].roleId}, "
        		+ "#'{'dataPermissionDefined.dataPermission[{0}].permissionValue}, sysdate, #'{'dataPermissionDefined.dataPermission[{0}].creater}, 0) ");
        for(int i = 0; i < entity.getDataPermission().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	public String addTaskDataRecordDetails(Map<String, TaskDataRecordSummary> map){
		TaskDataRecordSummary summary = map.get("summary");
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ALL ");
        MessageFormat mf = new MessageFormat("INTO taskdatarecord_detail(id,flow_id,data_id,model_id,system_id,task_type,remark,end_time,create_time,creater,status) "
        		+ "VALUES(sys_guid(),'','', summary.modelId, summary.bsId, summary.taskType, '', '', sysdate, summary.creater, 0) ");
        for(int i = 0; i < summary.getDetails().size(); i++) {
        	sb.append(mf.format(new Object[]{i}));
        }
        sb.append("SELECT 1 FROM DUAL");
        System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public String findEntityByPage(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		
		@SuppressWarnings("unchecked")
		Map<String,String> queryParam = (Map<String, String>) map.get("queryParam");
		PageQuery pageQuery = (PageQuery) map.get("page");
		//sb.append("SELECT * FROM mdm_user WHERE 1=1");
		sb.append("SELECT * FROM (SELECT ROWNUM AS rn, t.* FROM ").append(pageQuery.getTableName()).append(" t WHERE 1=1");
		System.out.println(map.get("conditions") );
		if(map.get("conditions") != null){
			sb.append(" ").append(map.get("conditions"));
		}
		sb.append(getQueryCondition(queryParam));
		sb.append(" AND ROWNUM <= ").append(pageQuery.getPageRowSize()*pageQuery.getCurrentPage()).append(") tt WHERE tt.rn > ");
		sb.append(pageQuery.getBeginIndex());
		/*if(pageQuery.getOrder() != null)
			sb.append(" ORDER BY ").append(pageQuery.getOrder());
		sb.append(" LIMIT ").append(pageQuery.getBeginIndex()).append(",").append(pageQuery.getPageRowSize());*/
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 查询表记录数
	 * @param map
	 * @return
	 */
	public String findEntityCount(Map<String, Object> map){
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("unchecked")
		Map<String,String> queryParam = (Map<String, String>) map.get("queryParam");
		String tableName = (String) map.get("tableName");
		sb.append("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE 1=1");
		if(map.get("conditions") != null){
			sb.append(" ").append(map.get("conditions"));
		}
		sb.append(getQueryCondition(queryParam));
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 拼接查询条件
	 * @param queryParam
	 * @return
	 */
	public String getQueryCondition(Map<String,String> queryParam){
		StringBuilder sb = new StringBuilder();
		for(String key : queryParam.keySet()){
			if(queryParam.get(key) != null && !queryParam.get(key).equals("")){
				sb.append(" AND ").append(key).append(" LIKE '%").append(queryParam.get(key)).append("%'");
			}
		}
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
		
	}
	
}
