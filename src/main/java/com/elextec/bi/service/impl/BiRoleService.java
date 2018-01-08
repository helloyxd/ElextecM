package com.elextec.bi.service.impl;

import com.elextec.bi.common.entity.VoResponse;

import com.elextec.bi.common.entity.constant.StatusEnum;
import com.elextec.bi.entity.BiMenu;
import com.elextec.bi.entity.BiRole;

import com.elextec.bi.mapper.BiRoleMapper;
import com.elextec.bi.service.BiBaseService;
import com.elextec.bi.service.IBiRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BiRoleService extends BiBaseService implements IBiRoleService {

	@Autowired
	private BiRoleMapper biRoleMapper;
	
//	@Autowired
//	private DataPermissionMapper dataPermissionMapper;

	@Override
	public List<BiRole> getAllRoles() {
		List<BiRole> list = biRoleMapper.findAll();
		return list;
	}
	
	@Override
	public BiRole getRoleById(String id) {
		BiRole role = biRoleMapper.findRoleById(id);
		return role;
	}

	@Override
	public VoResponse delRole(String id) {
		VoResponse voRes = new VoResponse();
		biRoleMapper.delete(id);
		return voRes;
	}

	@Override
	public VoResponse addRole(BiRole role) {
		VoResponse voRes = new VoResponse();
		List<BiRole> list = biRoleMapper.findRoleByName(role.getRoleName());
		if(list != null && list.size() > 0 ){
			voRes.setFail(voRes);
			voRes.setMessage("roleName is exist");
			return voRes;
		}
		role.setStatus(StatusEnum.StatusEnable);
		role.setCreater(getUserName());
		biRoleMapper.insert(role);
		return voRes;
	}

	@Override
	public VoResponse updateRole(BiRole role) {
		VoResponse voRes = new VoResponse();
		String msg = "";
		BiRole oldRole = biRoleMapper.findRoleById(role.getId());
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
				List<BiRole> list = biRoleMapper.findRoleByName(role.getRoleName());
				if(list != null && list.size() > 0 ){
					voRes.setFail(voRes);
					voRes.setMessage("roleName is exist");
					return voRes;
				}
			}
			biRoleMapper.update(role);
			msg = "角色更新成功;";
		}
		if(role.getMenus() != null){
			biRoleMapper.delRoleMenus(role.getId());
			biRoleMapper.addRoleMenus(role);
			msg += "角色上菜单更新成功;";
		}
		if(role.getDataPermissions() != null){
			biRoleMapper.delRoleDataPermission(role.getId());
			biRoleMapper.addRoleDataPermission(role);
			msg += "角色上数据权限更新成功;";
		}
		voRes.setMessage(msg);
		return voRes;
	}

	@Override
	public VoResponse updateRoleMenu(BiRole role) {
		VoResponse voRes = new VoResponse();
		biRoleMapper.delRoleMenus(role.getId());
		List<BiMenu> list = convertMenus(role.getMenus());
		role.setMenus(list);
		biRoleMapper.addRoleMenus(role);
		return voRes;
	}
	
	public List<BiMenu> convertMenus(List<BiMenu> menus){
		List<BiMenu> list = new ArrayList<BiMenu>();
		Iterator<BiMenu> it = menus.iterator();
		while (it.hasNext()) {
			BiMenu menu = it.next();
			list.add(menu);
			if(menu.getMenus() != null && menu.getMenus().size() > 0){
				List<BiMenu> list1 = convertMenus(menu.getMenus());
				list.addAll(list1);
			}
		}
		return list;
	}

//	@Override
//	public VoResponse addRoleDataPermission(DataPermission dataPermission) {
//		VoResponse voRes = new VoResponse();
//		dataPermissionMapper.insert(dataPermission);
//		return voRes;
//	}
}
