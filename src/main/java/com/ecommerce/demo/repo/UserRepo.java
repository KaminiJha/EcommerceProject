package com.ecommerce.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.model.User;


public interface UserRepo extends JpaRepository<User,Integer>{
	Optional<User> findUserByEmail(String email);
}
