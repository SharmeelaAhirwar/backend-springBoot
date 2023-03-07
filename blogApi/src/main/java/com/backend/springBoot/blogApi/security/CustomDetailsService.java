package com.backend.springBoot.blogApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.springBoot.blogApi.Entity.User;
import com.backend.springBoot.blogApi.Exception.ResourceNotFoundException;
import com.backend.springBoot.blogApi.Repositery.UserRepo;
@Service
public class CustomDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading username from db
		 User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user", "email"+username, 0));
		return user;
	}

}
