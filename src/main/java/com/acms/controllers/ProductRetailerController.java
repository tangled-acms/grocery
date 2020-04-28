package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.ProductRetailer;
import com.acms.models.ProductRetailerEmbeddedId;
import com.acms.services.ProductRetailerService;

@RestController
public class ProductRetailerController {
	
	@Autowired
	private ProductRetailerService productRetailerService;
	
	/*
	 * Retrieves all the records in productretailer table.
	 * METHOD = Get.
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/productretailer/getAll")
	public List<ProductRetailer> getAll() {
		return productRetailerService.getAll();

	}
	
	/*
	 * Retrieves the records in productretailer table with the given productId.
	 * METHOD = Get.
	 * REQUEST = productId.
	 * RESPONSE = List of all the records which contain that productId.
	 */
	@GetMapping("/productretailer/getById/{id}")
	public List<ProductRetailer> getByProductId(@PathVariable(value = "id") String productId) throws ResourceNotFoundException {
		return productRetailerService.getByProductId(productId);

	}
	
	/*
	 * Retrieves the records in productretailer table with the given composite Id.
	 * METHOD = Get.
	 * REQUEST = Object containing both productId and retailerId.
	 * RESPONSE = Object with that Id.
	 */
	@GetMapping("/productretailer/getById")
	public ProductRetailer getById(@RequestBody ProductRetailerEmbeddedId embeddedId) throws ResourceNotFoundException { 
		return productRetailerService.getByID(embeddedId);

	}
	
	/*
	 * Saves data in the productretailer table.
	 * METHOD = Post.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = Object of the saved record.
	 */
	@PostMapping("/productretailer/save")
	public ProductRetailer postData(@RequestBody ProductRetailer productRetailerDetails) {
		return productRetailerService.postData(productRetailerDetails);
	}
	
	/*
	 * Updates data in the productretailer table.
	 * METHOD = Put.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = Object of the updated record.
	 */
	@PutMapping("/productretailer/update")
	public ProductRetailer update(@RequestBody ProductRetailer productRetailerDetails) throws ResourceNotFoundException {
		return productRetailerService.update(productRetailerDetails);
	}
	/*
	 * Deletes data in the productretailer table.
	 * METHOD = Delete.
	 * REQUEST = Object containing ProductID of record that should be deleted.
	 * RESPONSE = productId and retailerId of the deleted record.
	 */
	@DeleteMapping("/productretailer/delete")
	public String delete(@RequestBody ProductRetailerEmbeddedId embeddedId) throws ResourceNotFoundException {
		return productRetailerService.delete(embeddedId);
	}
}
