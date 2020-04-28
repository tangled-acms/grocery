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
import com.acms.models.Billing;
import com.acms.services.BillingService;

@RestController
public class BillingController {
	
	@Autowired
	private BillingService billingService;
	
	/*
	 * Retrieves all the records in billing table.
	 * METHOD = Get
	 * REQUEST = null
	 * RESPONSE = List of all the records
	 */
	@GetMapping("/billing/getAll")
	public List<Billing> getAll() {
		return billingService.getAll();

	}
	
	/*
	 * Retrieves one record from billing table. 
	 * METHOD = Get. 
	 * REQUEST = Object containing billID. 
	 * RESPONSE = Object containing all the details of that bill.
	 */
	@GetMapping("/billing/getById/{id}")
	public Billing getById(@PathVariable(value = "id") int billId) throws ResourceNotFoundException {
		return billingService.getById(billId);

	}
	
	/*
	 * Saves data in the billing table.
	 * METHOD = Post
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId
	 */
	@PostMapping("/billing/save")
	public Billing postData(@RequestBody Billing billing) {
		return billingService.postData(billing);
	}
	
	/*
	 * Updates data in the billing table.
	 * METHOD = Put
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId
	 */
	@PutMapping("/billing/update/{id}")
	public Billing update(@PathVariable(value = "id") int billId, @Valid @RequestBody Billing billingDetails) throws ResourceNotFoundException {
		return billingService.update(billId, billingDetails);
	}
	
	/*
	 * Deletes data in the billing table.
	 * METHOD = Delete
	 * REQUEST = Object containing billId of record that should be deleted.
	 * RESPONSE = billId of the deleted record.
	 */
	@DeleteMapping("/billing/delete/{id}")
	public int delete(@PathVariable(value = "id") int billId) throws ResourceNotFoundException {
		return billingService.delete(billId);
	}
}
