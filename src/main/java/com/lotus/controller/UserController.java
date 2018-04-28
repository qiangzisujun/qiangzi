package com.lotus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lotus.model.User;
import com.lotus.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	@ResponseBody
	public String index(){
		User user=userService.selectByPrimaryKey(1);
		return user.toString();
	}
}