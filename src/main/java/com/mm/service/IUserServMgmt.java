package com.mm.service;


import com.mm.entity.UserInfo;

public interface IUserServMgmt {
	
	public String registerUser(UserInfo info);

	public String verify(UserInfo user);
	
}
