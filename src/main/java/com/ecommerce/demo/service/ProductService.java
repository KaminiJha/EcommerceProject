package com.ecommerce.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repo.ProductRepo;


@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepo;
	
	public List<Product> getAllproduct(){
		return productRepo.findAll();
	}
	
	public void addProduct(Product product) {
		productRepo.save(product);
	}
	
	public void removeProductById(long id){
		productRepo.deleteById(id);
	}
	
	public Optional<Product> getProductById(long id){
		return productRepo.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryID(int id){
		return productRepo.findAllByCategory_Id(id);
	}
}