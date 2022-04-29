package com.ecommerce.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.model.Product;


public interface ProductRepo extends JpaRepository<Product, Long>{

	public List<Product> findAllByCategory_Id(int id);

}