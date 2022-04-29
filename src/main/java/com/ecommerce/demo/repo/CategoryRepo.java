package com.ecommerce.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.model.Category;



public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
