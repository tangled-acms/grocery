package com.acms.controllers;

import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acms.models.Product;
import com.acms.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product/getAll")
	public Iterable<Product> getAll(){
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
	
	@PostMapping("/product/save")
	public String postData(@RequestParam int serialNumber,@RequestParam String productId,@RequestParam int timeStamp,@RequestParam String description,@RequestParam String name,@RequestParam double MRP,@RequestParam int quantity,@RequestParam double promotion){
		return productService.postData(serialNumber,productId,timeStamp,description,name,MRP,quantity,promotion);
	}

}
