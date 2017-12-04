package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.entity.User;
import com.elextec.mdm.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/registered")
	public Boolean registered(@RequestBody User user) {
		user.setUserName("zkj");
		user.setUserPassword("");
		Boolean result = userService.registerUser(user);
		return result;
	}
	
	@GetMapping("/get")
	public Object get() {
		return userService.getAll();
	}
	

}
