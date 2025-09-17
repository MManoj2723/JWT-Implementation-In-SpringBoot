package com.mm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mm.entity.UserInfo;
import com.mm.service.IUserServMgmt;

@RestController

public class UserOpeartionsController {
	
	@Autowired
	private IUserServMgmt serv;
	
	
	@PostMapping("/register")
	public String registerTheUser(@RequestBody UserInfo info) {
		System.out.println("UserOpeartionsController.registerTheUser()-start");
		
		String registerUser = serv.registerUser(info);
		
		System.out.println("UserOpeartionsController.registerTheUser()-end");
		return registerUser;
	}
	
	
	
	
	
	@PostMapping("/login")
	public String login(@RequestBody UserInfo user) {
		
		System.out.println(user);
		
		return serv.verify(user);
	}
	
	

}















