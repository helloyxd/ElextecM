package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.service.IRoleService;
import com.elextec.mdm.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@PostMapping("/registered")
	public Boolean registered(@RequestBody User user) {
		if(("").equals(user.getUserName())){
			return false;
		}else if(("").equals(user.getUserPassword())){
			return false;
		}
		VoResult vor = userService.registerUser(user);
		return vor.getResult();
	}
	
	@GetMapping("/getAll")
	public Object getAll() {
		return userService.getAll();
	}
	

}
