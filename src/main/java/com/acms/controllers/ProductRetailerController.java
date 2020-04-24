package com.acms.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.acms.models.ProductRetailer;


public class ProductRetailerController {
	/*
	 * Retrieves all the records in productretailer table.
	 * METHOD = Get.
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/productretailer/getAll")
	public List<ProductRetailer> getAll() {
		return null;

	}
	
	/*
	 * Saves data in the productretailer table.
	 * METHOD = Post.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = productId and retailerId of the saved record.
	 */
	@PostMapping("/productretailer/save")
	public String postData() {
		return null;
	}
	
	/*
	 * Updates data in the productretailer table.
	 * METHOD = Put.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = productID and retailerID of the updated record.
	 */
	@PutMapping("/productretailer/update")
	public String update() {
		return null;
	}
	/*
	 * Deletes data in the productretailer table.
	 * METHOD = Delete.
	 * REQUEST = Object containing ProductID of record that should be deleted.
	 * RESPONSE = ProductID of the deleted record.
	 */
	@DeleteMapping("/productretailer/delete")
	public String delete() {
		return null;
	}
}
