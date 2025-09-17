package com.mm.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mm.entity.UserInfo;
import com.mm.repo.IUserInfoRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserInfoRepo repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("UserServImpl.loadUserByUsername()-start");
		
		UserInfo details = 
							repo.findUserByName(username)
							.orElseThrow(()->new IllegalArgumentException("User Not Found"));
		
		User user = new User(
								details.getName(),
								details.getPassword(), 
								Collections.singleton(new SimpleGrantedAuthority("USER"))
							);
		
		
		System.out.println("UserServImpl.loadUserByUsername()-end");
		
		return user;
	}

}
