package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acms.models.Product;
import com.acms.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	/*
	 * Retrieves all the records in product table.
	 * METHOD = Get.
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/product/getAll")
	public List<Product> getAll() {
		return productService.getAll();

	}
	
	/*
	 * Retrieves one record from product table.
	 * METHOD = Get.
	 * REQUEST = Object containing productID.
	 * RESPONSE = Object containing all the details of that product.
	 */
	@GetMapping("/product/getById")
	public Product getById(@RequestParam String productId) {
		return productService.getById(productId);

	}
	
	/*
	 * Retrieves all the records in product table.
	 * METHOD = Get
	 * REQUEST = Object containing productName
	 * RESPONSE = Object containing all the details of that product.
	 */
	@GetMapping("/product/getByName")
	public Product getByName(@RequestParam String name) {
		return null;

	}
	
	/*
	 * Saves data in the product table.
	 * METHOD = Post.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = productID of the saved record.
	 */
	@PostMapping("/product/save")
	public String postData(@RequestParam String productId, @RequestParam String description, @RequestParam String name,
			@RequestParam double MRP, @RequestParam int quantity, @RequestParam double promotion) {
		return productService.postData(productId, description, name, MRP, quantity, promotion);
	}
	
	/*
	 * Updates data in the product table.
	 * METHOD = Put.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = productID of the updated record.
	 */
	@PutMapping("/product/update")
	public String update() {
		return null;
	}
	
	/*
	 * Deletes data in the product table.
	 * METHOD = Delete
	 * REQUEST = Object containing productID of record that should be deleted.
	 * RESPONSE = productID of the deleted record.
	 */
	@DeleteMapping("/product/delete")
	public String delete() {
		return null;
	}
}
