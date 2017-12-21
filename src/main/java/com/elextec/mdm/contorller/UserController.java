package com.elextec.mdm.contorller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController{

	@Autowired
	private IUserService userService;
	
	@PostMapping
	public Object add(@RequestBody User user) {
		VoResponse voRes = new VoResponse();
		if(("").equals(user.getUserName())){
			voRes.setNull(voRes);
			voRes.setMessage("userName is null");
		}else if(("").equals(user.getUserPassword())){
			voRes.setNull(voRes);
			voRes.setMessage("password is null");
		}else{
			VoResult vor = userService.add(user);
			if(!vor.getResult()){
				voRes.setFail(voRes);
			}
			voRes.setMessage(vor.getMsg());
		}
		return voRes;
	}
	
	@PostMapping("/signIn")
	public Object signIn(@RequestBody User user, @RequestParam(required=false) Boolean isMarked,
			HttpServletRequest request, HttpServletResponse response){
		VoResponse voRes = userService.signIn(user.getUserName(), user.getUserPassword());
		if(voRes.getSuccess()){
			user = (User) voRes.getData();
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			user.setSessionId(sessionId);
			session.setAttribute("mdm_user", user);
			Cookie[] cookies = request.getCookies();
			if(isMarked!= null && isMarked){
				Cookie cook = new Cookie("sessionId", sessionId);
				response.addCookie(cook);
			}
			List<Role> roles = user.getRoles();
			List<Menu> menus = new ArrayList<>();
			Iterator<Role> it = roles.iterator();
			while(it.hasNext()){
				Role role = it.next();
				if(menus.size() == 0){
					menus.addAll(role.getMenus());
					continue;
				}
				List<Menu> listMenu = role.getMenus();
				Iterator<Menu> itMenu;
				boolean flag;
				for(Menu e : listMenu){
					flag = false;
					itMenu = menus.iterator();
					while(itMenu.hasNext()){
						Menu menu = itMenu.next();
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
			session.setAttribute("mdm_right", menus);
			voRes.setData(user);
		}
		return voRes;
	}
	
	@PostMapping("/signOut")
	public Object signOut(HttpServletRequest request, HttpServletResponse response){
		VoResponse voRes = new VoResponse();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("mdm_user");
		session.removeAttribute("mdm_user");
		return voRes;
	}
	
	@GetMapping("/getAll")
	public Object getAll() {
		VoResponse voRes = new VoResponse();
		voRes.setData(userService.getAll());
		return voRes;
	}
	
	@GetMapping("/getPage")
	public Object getPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "userName") String userName) {
		VoResponse voRes = new VoResponse();
		//logger.debug("/getPage");
		//logger.error("test");
		//logger.warn("11111");
		User user = new User();
		user.setUserName(userName);
		voRes.setData(userService.getPage(user, page, pageSize));
		return voRes;
	}
	
	@DeleteMapping
	public Object del(@RequestParam("id") String userId) {
		VoResponse voRes = userService.del(userId);
		return voRes;
	}
	
	@PutMapping
	public Object update(@RequestBody User user) {
		VoResponse voRes = userService.update(user);
		return voRes;
	}
	
	@GetMapping
	public Object getUser(@RequestParam("id") String userId){
		VoResponse voRes = new VoResponse();
		User user = userService.getById(userId);
		voRes.setData(user);
		return voRes;
	}
	
	public static void main(String[] args) {
	}
}
