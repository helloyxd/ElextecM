package com.elextec.mdm.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/mdm/user")
public class UserController{

	public static HashMap<String,String> userMap = new HashMap<String,String>();
	
	public static HashMap<String, HttpSession> sessionMap = new HashMap<String,HttpSession>();
	
	@Autowired
	private IUserService userService;
	
	//使session失效,并移除map
    public void destroyed(String username){
        String sessionid = userMap.get(username);
        if(sessionid != null) {
        	HttpSession session = sessionMap.get(sessionid);
        	if(session != null) {
        		try {
        			session.invalidate();
            		sessionMap.remove(sessionid);
        		}catch (Exception e) {
					e.printStackTrace();
				}
        	}
            userMap.remove(username);
        }
    }

    //将session信息存入map
    public void created(String username, HttpSession session) {
        userMap.put(username, session.getId());
        sessionMap.put(session.getId(), session);
    }

	@PostMapping("/signIn")
	public Object signIn(@RequestBody User user, @RequestParam(required=false, defaultValue="false") Boolean isMarked,
			HttpServletRequest request, HttpServletResponse response){
		VoResponse voRes = userService.signIn(user.getUserName(), user.getUserPassword());
		if(voRes.getSuccess()){
			//保存session到map
			destroyed(user.getUserName());
			
			user = (User) voRes.getData();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(-1);
			String sessionId = session.getId();
			user.setSessionId(sessionId);
			user.setUserPassword("");
			session.setAttribute("mdm_user", user);
			created(user.getUserName(), session);
			
			Cookie[] cookies = request.getCookies();
			if(isMarked){
				boolean flag = false;
				String value = user.getUserName()+"&&"+user.getUserPassword();
				for(Cookie cook : cookies){
					if(cook.getName().equals("logineduser")){
						cook.setValue(value);
						response.addCookie(cook);
						flag = true;
						continue;
					}
				}
				if(!flag){
					Cookie cook = new Cookie("logineduser", value);
					cook.setMaxAge(60 * 60 * 24 * 7);
					cook.setHttpOnly(true);
					response.addCookie(cook);
				}
			}else{
				Cookie cook = new Cookie("logineduser", null);
				cook.setMaxAge(0);
				response.addCookie(cook);
			}
			List<Role> roles = user.getRoles();
			List<Menu> menus = new ArrayList<>();
			user.setMenus(userService.getUserMenuById(user.getId()));
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
		if(user != null){
			session.removeAttribute("mdm_user");
			session.removeAttribute("mdm_right");
		}
		return voRes;
	}
	
	@PostMapping
	public Object add(@RequestBody User user) {
		VoResponse voRes = new VoResponse();
		if(("").equals(user.getUserName())){
			voRes.setNull(voRes);
			voRes.setMessage("用户名不能为空");
		}else if(("").equals(user.getUserPassword())){
			voRes.setNull(voRes);
			voRes.setMessage("密码不能为空");
		}else{
			VoResult vor = userService.add(user);
			if(!vor.getResult()){
				voRes.setFail(voRes);
			}
			voRes.setMessage(vor.getMsg());
		}
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
			@RequestParam(value = "userName", required = false) String userName) {
		VoResponse voRes = new VoResponse();
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
	
	@GetMapping("getUserMenus")
	public Object getCurrentUserMenus(){
		VoResponse voRes = new VoResponse();
		voRes.setData(userService.getCurrentUserMenus());
		return voRes;
	}
	
	@PostMapping("addUserRoles")
	public Object addUserRoles(@RequestBody List<User> users){
		VoResponse voRes = userService.updateUserRole(users);
		return voRes;
	}
}
