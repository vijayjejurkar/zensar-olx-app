package com.app.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.olx.entity.UserEntityAuth;
import com.app.olx.repo.UserRepoAuth;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepoAuth userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntityAuth> userList = userRepo.findByUsername(username);
		
		if(userList == null | userList.size() ==0) {
			throw new UsernameNotFoundException(username);
		}
		UserEntityAuth userEntity = userList.get(0);
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
		User user = new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
		
		return user;
	}

}
