package com.mm.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mm.service.JWTService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService serv;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JWtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		System.out.println("SecurityConfig.filterChain()-start");
		
		http.authorizeHttpRequests(
				
				request->request
				.requestMatchers("/register","/login").permitAll()
				.anyRequest().authenticated()
				
				);
		
		http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // For StateLess API						
		
		http.csrf(csrf->csrf.disable());
		
		http.httpBasic(withDefaults());
		
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
//		http.formLogin(form->form.defaultSuccessUrl("/home",true).permitAll());
		
		System.out.println("SecurityConfig.filterChain()-end");
		
		 return http.build();
	}
	
	
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		System.out.println("SecurityConfig.authenticationProvider()-start");
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(serv);
	    provider.setPasswordEncoder(encoder);
	    
	    System.out.println("SecurityConfig.authenticationProvider()-end");
	    return provider;
 
		
	}
	
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	

}
































