package com.ecommerce.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.model.CustomUserDetail;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repo.UserRepo;



@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	UserRepo userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("user not found"));
		return user.map(CustomUserDetail::new).get();
	}
	
}