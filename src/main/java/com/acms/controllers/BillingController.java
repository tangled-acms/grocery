package com.acms.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.acms.models.Billing;


public class BillingController {
	/*
	 * Retrieves all the records in billing table.
	 * METHOD = Get
	 * REQUEST = null
	 * RESPONSE = List of all the records
	 */
	@GetMapping("/billing/getAll")
	public List<Billing> getAll() {
		return null;

	}
	
	/*
	 * Saves data in the billing table.
	 * METHOD = Post
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId
	 */
	@PostMapping("/billing/save")
	public String postData() {
		return null;
	}
	
	/*
	 * Updates data in the billing table.
	 * METHOD = Put
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId
	 */
	@PutMapping("/billing/update")
	public String update() {
		return null;
	}
	/*
	 * Deletes data in the billing table.
	 * METHOD = Delete
	 * REQUEST = Object containing billId of record that should be deleted.
	 * RESPONSE = billId of the deleted record.
	 */
	@DeleteMapping("/billing/delete")
	public String delete() {
		return null;
	}
}
