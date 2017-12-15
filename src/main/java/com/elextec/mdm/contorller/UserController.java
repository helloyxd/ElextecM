package com.elextec.mdm.contorller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
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
		}
		return voRes;
	}
	
	@GetMapping("/getAll")
	public Object getAll() {
		VoResponse voResponse = new VoResponse();
		voResponse.setData(userService.getAll());
		return voResponse;
	}
	
	@GetMapping("/getPage")
	public Object getPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "userName") String userName) {
		VoResponse voResponse = new VoResponse();
		System.out.println(userName);
		User user = new User();
		user.setUserName(userName);
		voResponse.setData(userService.getPage(user, page, pageSize));
		return voResponse;
	}
	

}
