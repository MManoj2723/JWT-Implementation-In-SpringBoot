package com.mm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mm.entity.UserInfo;

public interface IUserInfoRepo extends JpaRepository<UserInfo, Integer>{
	
	@Query("from UserInfo where name=?1")
	public Optional<UserInfo> findUserByName(String usrname);
	
}
