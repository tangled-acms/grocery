package com.acms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Product;
import com.acms.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	/*
	 * Retrieves all the records in product table. 
	 * METHOD = Get. 
	 * REQUEST = null.
	 * RESPONSE = List of objects of Product type.
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
	@GetMapping("/product/getById/{id}")
	public Product getById(@PathVariable(value = "id") String productId) throws ResourceNotFoundException {
		return productService.getById(productId);

	}

	/*
	 * Saves data in the product table. 
	 * METHOD = Post. 
	 * REQUEST = Object containing all the details of the attributes. 
	 * RESPONSE = Object containing all the details of the saved record.
	 */
	@PostMapping("/product/save")
	public Product postData(@RequestBody Product product) {
		return productService.postData(product);
	}

	/*
	 * Updates data in the product table. 
	 * METHOD = Put. 
	 * REQUEST = Object containing
	 * all the details of the attributes. 
	 * RESPONSE = Object containing all the details of the updated record.
	 */
	@PutMapping("/product/update/{id}")
	public Product update(@PathVariable(value = "id") String productId, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		return productService.update(productId, productDetails);

	}

	/*
	 * Deletes data in the product table. 
	 * METHOD = Delete 
	 * REQUEST = Object containing productID of record that should be deleted. 
	 * RESPONSE = productID of the deleted record.
	 */
	@DeleteMapping("/product/delete/{id}")
	public String delete(@PathVariable(value = "id") String productId) throws ResourceNotFoundException {
		return productService.delete(productId);
	}
}
