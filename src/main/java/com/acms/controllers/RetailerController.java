package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acms.models.Retailer;
import com.acms.services.RetailerService;

@RestController
public class RetailerController {
	
	@Autowired
	RetailerService retailerService;
	
	/*
	 * Retrieves all the records in retailer table.
	 * METHOD = Get.
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/retailer/getAll")
	public List<Retailer> getAll() {
		return null;

	}
	
	/*
	 * Saves data in the retailer table.
	 * METHOD = Post.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = retailerID of the new record.
	 */
	@PostMapping("/retailer/save")
	public String postData() {
		return null;
	}
	
	/*
	 * Updates data in the retailer table.
	 * METHOD = Put.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = retailerID of the updated record.
	 */
	@PutMapping("/retailer/update")
	public String update() {
		return null;
	}
	/*
	 * Deletes data in the retailer table.
	 * METHOD = Delete
	 * REQUEST = Object containing retailerID of record that should be deleted.
	 * RESPONSE = retailerID of the deleted record.
	 */
	@DeleteMapping("/retailer/delete")
	public String delete() {
		return null;
	}

	
	
}
