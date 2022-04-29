package com.ecommerce.demo.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ecommerce.demo.model.Role;
import com.ecommerce.demo.repo.RoleRepo;
import com.ecommerce.demo.repo.UserRepo;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	UserRepo userRepo;

	private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, Authentication authentication) throws IOException{
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
		String email=token.getPrincipal().getAttributes().get("email").toString();
		
		if(userRepo.findUserByEmail(email).isPresent()) {
		
		}else {
			com.ecommerce.demo.model.User user = new com.ecommerce.demo.model.User();
		user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
		user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
		user.setEmail(email);
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRoles(roles);
		userRepo.save(user);
		}
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
	}

}