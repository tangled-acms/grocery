package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acms.models.Product;
import com.acms.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product/getAll")
	public List<Product> getAll(){
		return productService.getAll();
	
	}
	
	@GetMapping("/product/getById")
	public Product getById(){
		return productService.getById();
	
	}
	
	@GetMapping("/product/getByName")
	public Product getByName(){
		return productService.getByName();
	
	}
	
	@PostMapping("/product/putAll")
	public String putAll(){
		return productService.putAll();
	
	}

}
