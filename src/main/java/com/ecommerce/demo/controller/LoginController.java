package com.ecommerce.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.demo.global.GlobalData;
import com.ecommerce.demo.model.Role;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repo.RoleRepo;
import com.ecommerce.demo.repo.UserRepo;



@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;



	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet() { return "register";}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException{
		String password=user.getPassword();
		String email=user.getEmail();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRoles(roles);
		userRepo.save(user);
		request.login(email,password);
		return "redirect:/";
	}
}
