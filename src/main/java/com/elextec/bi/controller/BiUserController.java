package com.elextec.bi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.elextec.bi.common.entity.VoResponse;
import com.elextec.bi.common.entity.VoResult;
import com.elextec.bi.entity.BiMenu;
import com.elextec.bi.entity.BiRole;
import com.elextec.bi.entity.BiUser;
import com.elextec.bi.service.IBiUserService;
import com.elextec.bi.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("bi/user")
public class BiUserController {

	@Autowired
	private IBiUserService biUserService;
	
	@PostMapping
	public Object add(@RequestBody BiUser user) {
		VoResponse voRes = new VoResponse();
		if(("").equals(user.getUserName())){
			voRes.setNull(voRes);
			voRes.setMessage("userName is null");
		}else if(("").equals(user.getUserPassword())){
			voRes.setNull(voRes);
			voRes.setMessage("password is null");
		}else{
			VoResult vor = biUserService.add(user);
			if(!vor.getResult()){
				voRes.setFail(voRes);
			}
			voRes.setMessage(vor.getMsg());
		}
		return voRes;
	}
	
	@PostMapping("/signIn")
	public Object signIn(@RequestBody BiUser user, @RequestParam(required=false) Boolean isMarked,
			HttpServletRequest request, HttpServletResponse response){
		VoResponse voRes = biUserService.signIn(user.getUserName(), MD5Util.encode(user.getUserPassword()));
		if(voRes.getSuccess()){
			user = (BiUser) voRes.getData();
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			user.setSessionId(sessionId);
			Cookie[] cookies = request.getCookies();
			if(isMarked!= null && isMarked){
				Cookie cook = new Cookie("sessionId", sessionId);
				response.addCookie(cook);
			}
			List<BiRole> roles = user.getRoles();
			List<BiMenu> menus = new ArrayList<>();
			Iterator<BiRole> it = roles.iterator();
			//封装数据角色的数据权限
			Map<String,List<Map<String,Object>>> dataMap = new HashMap<String,List<Map<String,Object>>>();
			while(it.hasNext()){
				BiRole role = it.next();
				//将角色里的数据权限JSON拆为一个LIST对象
				List<Map<String, Object>> mapList = JSON.parseObject(role.getRoleDataPermissions(), new TypeReference<List<Map<String, Object>>>() {
				});
				for(Map<String,Object> map:mapList){
					//查找是否已经存在对应数据权限ID的LIST
					List<Map<String,Object>> dataList = dataMap.get(map.get("datapermissionId").toString());
					Object temp = map.get("val");
					List<Map<String,Object>> t1 = (List<Map<String,Object>>)temp;
					if(dataList != null){
						//如果存在，将新的LIST加入进去
						for(Map<String,Object> mapTest:t1){
							dataList.add(mapTest);
						}
						//将新的LIST去重复后覆盖掉原MAP中的LIST
						dataMap.put(map.get("datapermissionId").toString(),distinct(dataList));
					}else{
						//如果不存在，直接将LIST放过dataMap中，以数据权限的ID为KEY
						dataMap.put(map.get("datapermissionId").toString(),t1);
					}
				}
				if(menus.size() == 0){
					menus.addAll(role.getMenus());
					continue;
				}
				List<BiMenu> listMenu = role.getMenus();
				Iterator<BiMenu> itMenu;
				boolean flag;
				for(BiMenu e : listMenu){
					flag = false;
					itMenu = menus.iterator();
					while(itMenu.hasNext()){
						BiMenu menu = itMenu.next();
						if(e.getId().equals(menu.getId())){
							flag = true;
							continue;
						}
					}
					if(!flag){
						menus.add(e);
					}
				}
			}
			user.setMenus(menus);
//			session.setAttribute("bi_right", menus);
			session.setAttribute("bi_user", user);
			user.setDataPermissions(dataMap);
			voRes.setData(user);
		}
		return voRes;
	}
	
	@PostMapping("/signOut")
	public Object signOut(HttpServletRequest request, HttpServletResponse response){
		VoResponse voRes = new VoResponse();
		HttpSession session = request.getSession();
		BiUser user = (BiUser) session.getAttribute("bi_user");
		session.removeAttribute("mdm_user");
		return voRes;
	}
	
	@GetMapping("/getAll")
	public Object getAll() {
		VoResponse voRes = new VoResponse();
		voRes.setData(biUserService.getAll());
		return voRes;
	}
	
	@GetMapping("/getPage")
	public Object getPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "userName", required = false) String userName) {
		VoResponse voRes = new VoResponse();
		BiUser user = new BiUser();
		user.setUserName(userName);
		voRes.setData(biUserService.getPage(user, page, pageSize));
		return voRes;
	}

	@PostMapping("/addUserRole")
	public Object addUserRole(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("userId");
        String rolesId = request.getParameter("rolesId");
        String[] roles = rolesId.split(",");
		VoResponse voRes = biUserService.addUserRoles(userId,roles);
//		HttpSession session = request.getSession();
//		BiUser user = (BiUser) session.getAttribute("bi_user");
//		session.removeAttribute("bi_user");
		return voRes;
	}

	@PostMapping("/updateUserRole")
	public Object updateUserRole(HttpServletRequest request, HttpServletResponse response){
		String userId = request.getParameter("userId");
		String rolesId = request.getParameter("rolesId");
		String[] roles = rolesId.split(",");
		VoResponse voRes = biUserService.updateUserRole(userId,roles);
//		HttpSession session = request.getSession();
//		BiUser user = (BiUser) session.getAttribute("bi_user");
//		session.removeAttribute("bi_user");
		return voRes;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String userId) {
		VoResponse voRes = biUserService.del(userId);
		return voRes;
	}
	
	@PutMapping
	public Object update(@RequestBody BiUser user) {
		VoResponse voRes = biUserService.update(user);
		return voRes;
	}

	@GetMapping
	public Object getUser(@RequestParam("id") String userId){
		VoResponse voRes = new VoResponse();
		BiUser user = biUserService.getById(userId);
		voRes.setData(user);
		return voRes;
	}

	/**
	 * 将数据权限的LIST去重
	 * */
	private List<Map<String,Object>> distinct(List<Map<String,Object>> map){
		Set set = new  HashSet();
		List<Map<String,Object>> newList = new  ArrayList<Map<String,Object>>();
		for (Map<String,Object> cd:map) {
			if(set.add(cd.toString())){
				newList.add(cd);
			}
		}
		return newList;
	}
}
