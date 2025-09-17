package com.mm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mm.entity.UserInfo;
import com.mm.repo.IUserInfoRepo;

@Service
public class UserServImpl implements IUserServMgmt{

	@Autowired
	private IUserInfoRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private  AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	public String registerUser(UserInfo info) {	
		System.out.println("UserServImpl.registerUser()-start");
		
		if(repo.findUserByName(info.getName()).isPresent()) {
			return "User Already Exists With That Name";
		}
		
		info.setPassword(encoder.encode(info.getPassword()));
		
		UserInfo save = repo.save(info);
		
		
		
		System.out.println("UserServImpl.registerUser()-end");
		return "User Registerd With id:"+save.getId();
	}
	
	
	
	
	
	@Override
	public String verify(UserInfo user) {
		
		Authentication authenticate = authManager
										.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
				
				if(authenticate.isAuthenticated()) {
					
					return jwtService.generateToken(user.getName());
				}
				else {
					return "fail";
				}
	}





	
















	
	
	


}





































