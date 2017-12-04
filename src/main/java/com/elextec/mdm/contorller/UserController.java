package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.entity.User;
import com.elextec.mdm.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/registered")
	public Boolean registered() {
		User user = new User();
		user.setUserName("zkj");
		user.setUserPassword("");
		Boolean result = userService.registerUser(user);
		return result;
	}
	

}
