package com.acms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.acms.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

}
