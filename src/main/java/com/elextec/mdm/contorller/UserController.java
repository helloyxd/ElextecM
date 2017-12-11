package com.elextec.mdm.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elextec.mdm.common.entity.ResponseCodeEnum;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.common.entity.VoResult;
import com.elextec.mdm.entity.User;
import com.elextec.mdm.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController{

	@Autowired
	private IUserService userService;
	
	@PostMapping("/registered")
	public Object registered(@RequestBody User user) {
		VoResponse voResponse = new VoResponse();
		if(("").equals(user.getUserName())){
			voResponse.setCode(ResponseCodeEnum.CodeInputDataException);
			voResponse.setMessage("userName is null");
		}else if(("").equals(user.getUserPassword())){
			voResponse.setCode(ResponseCodeEnum.CodeInputDataException);
			voResponse.setMessage("password is null");
		}else{
			VoResult vor = userService.registerUser(user);
			if(!vor.getResult()){
				voResponse.setCode(ResponseCodeEnum.CodeFail);
			}
			voResponse.setMessage(vor.getMsg());
		}
		return voResponse;
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
		User user = new User();
		user.setUserName(userName);
		voResponse.setData(userService.getPage(user, page, pageSize));
		return voResponse;
	}
	

}
