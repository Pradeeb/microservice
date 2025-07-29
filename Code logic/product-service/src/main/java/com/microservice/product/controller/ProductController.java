package com.microservice.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.product.entity.Product;
import com.microservice.product.repository.IProductRepo;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private IProductRepo productRepo;
	
	// create a product
	@PostMapping
	public Product addproduct(@RequestBody Product product) {
		return productRepo.save(product);
	}
	
	// get products
	@GetMapping
	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}

	// get product
	@GetMapping("/{productid}")
	public Optional<Product> getProduct(@PathVariable("productid") Long productid) {
		return productRepo.findById(productid);
	}

	
}
