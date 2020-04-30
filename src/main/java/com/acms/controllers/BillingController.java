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
	
	/**
	 * Retrieves all the records in billing table.
	 * METHOD = Get
	 * REQUEST = null
	 * @param null
	 * @return List of all the records
	 */
	@GetMapping("/billing/getAll")
	public List<Billing> getAllBillingRecords() {
		return this.billingService.getAllBillingRecords();

	}
	
	/**
	 * Retrieves one record from billing table. 
	 * METHOD = Get. 
	 * @param Object containing billID. 
	 * @return Object containing all the details of that bill.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@GetMapping("/billing/getById/{id}")
	public Billing getByBillId(@PathVariable(value = "id") int billId) throws ResourceNotFoundException {
		return this.billingService.getByBillId(billId);

	}
	
	/**
	 * Saves data in the billing table.
	 * METHOD = Post
	 * @param Object containing all the details of the attributes.
	 * @return billId of the saved data
	 */
	@PostMapping("/billing/save")
	public Billing postDataToBillingTable(@RequestBody Billing billing) {
		return this.billingService.postDataToBillingTable(billing);
	}
	
	/**
	 * Updates data in the billing table.
	 * METHOD = Put
	 * @param Object containing all the details of the attributes.
	 * @return billId
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@PutMapping("/billing/update/{id}")
	public Billing updateBillingRecord(@PathVariable(value = "id") int billId, @Valid @RequestBody Billing billingDetails) throws ResourceNotFoundException {
		return this.billingService.updateBillingRecord(billId, billingDetails);
	}
	
	/**
	 * Deletes data in the billing table.
	 * METHOD = Delete
	 * @param Object containing billId of record that should be deleted.
	 * @return billId of the deleted record.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@DeleteMapping("/billing/delete/{id}")
	public int deleteBillingRecord(@PathVariable(value = "id") int billId) throws ResourceNotFoundException {
		return this.billingService.deleteBillingRecord(billId);
	}
}
