package com.elextec.bi.controller;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
			session.setAttribute("bi_user", user);
			Cookie[] cookies = request.getCookies();
			if(isMarked!= null && isMarked){
				Cookie cook = new Cookie("sessionId", sessionId);
				response.addCookie(cook);
			}
			List<BiRole> roles = user.getRoles();
			List<BiMenu> menus = new ArrayList<>();
			Iterator<BiRole> it = roles.iterator();
			while(it.hasNext()){
				BiRole role = it.next();
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
			session.setAttribute("bi_right", menus);
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
//		voRes.setData(userService.getPage(user, page, pageSize));
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
}
