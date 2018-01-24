package com.elextec.mdm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.entity.DataPermission;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.mapper.DataPermissionMapper;
import com.elextec.mdm.mapper.RoleMapper;
import com.elextec.mdm.service.BaseService;
import com.elextec.mdm.service.IRoleService;

@Service
public class RoleService extends BaseService implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private DataPermissionMapper dataPermissionMapper;

	@Override
	public List<Role> getAllRoles() {
		List<Role> list = roleMapper.findAll();
		return list;
	}
	
	@Override
	public Role getRoleById(String id) {
		Role role = roleMapper.findRoleById(id);
		return role;
	}

	@Override
	public VoResponse delRole(String id) {
		VoResponse voRes = new VoResponse();
		roleMapper.delete(id);
		return voRes;
	}

	@Override
	public VoResponse addRole(Role role) {
		VoResponse voRes = new VoResponse();
		List<Role> list = roleMapper.findRoleByName(role.getRoleName());
		if(list != null && list.size() > 0 ){
			voRes.setFail(voRes);
			voRes.setMessage("roleName is exist");
			return voRes;
		}
		role.setCreater(getUserName());
		roleMapper.insert(role);
		return voRes;
	}

	@Override
	public VoResponse updateRole(Role role) {
		VoResponse voRes = new VoResponse();
		String msg = "";
		Role oldRole = roleMapper.findRoleById(role.getId());
		boolean flag = false;
		if(oldRole == null){
			voRes.setNull(voRes);
			return voRes;
		}else if(role.getRoleName() != null && !oldRole.getRoleName().equals(role.getRoleName())){
			flag = true;
		}else if(role.getRoleDesc() != null &&  !oldRole.getRoleDesc().equals(role.getRoleDesc())){
			flag = true;
		}
		if(flag){
			if(!oldRole.getRoleName().equals(role.getRoleName())){
				List<Role> list = roleMapper.findRoleByName(role.getRoleName());
				if(list != null && list.size() > 0 ){
					voRes.setFail(voRes);
					voRes.setMessage("roleName is exist");
					return voRes;
				}
			}
			roleMapper.update(role);
			msg = "角色更新成功;";
		}
		if(role.getMenus() != null){
			roleMapper.delRoleMenus(role.getId());
			roleMapper.addRoleMenus(role);
			msg += "角色上菜单更新成功;";
		}
		if(role.getDataPermissions() != null){
			roleMapper.delRoleDataPermission(role.getId());
			roleMapper.addRoleDataPermission(role);
			msg += "角色上数据权限更新成功;";
		}
		voRes.setMessage(msg);
		return voRes;
	}

	@Override
	public VoResponse updateRoleMenu(Role role) {
		VoResponse voRes = new VoResponse();
		roleMapper.delRoleMenus(role.getId());
		List<Menu> list = convertMenus(role.getMenus());
		role.setMenus(list);
		roleMapper.addRoleMenus(role);
		return voRes;
	}
	
	public List<Menu> convertMenus(List<Menu> menus){
		List<Menu> list = new ArrayList<Menu>();
		Iterator<Menu> it = menus.iterator();
		while (it.hasNext()) {
			Menu menu = it.next();
			list.add(menu);
			if(menu.getMenus() != null && menu.getMenus().size() > 0){
				List<Menu> list1 = convertMenus(menu.getMenus());
				list.addAll(list1);
			}
		}
		return list;
	}

	@Override
	public VoResponse addRoleDataPermission(DataPermission dataPermission) {
		VoResponse voRes = new VoResponse();
		dataPermissionMapper.insert(dataPermission);
		return voRes;
	}
}
