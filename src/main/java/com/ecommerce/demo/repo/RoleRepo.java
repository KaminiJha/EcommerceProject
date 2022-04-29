package com.ecommerce.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.model.Role;



public interface RoleRepo extends JpaRepository<Role, Integer>{
}
