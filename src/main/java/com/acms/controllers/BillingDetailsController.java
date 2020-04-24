package com.acms.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.acms.models.BillingDetails;



public class BillingDetailsController {
	/*
	 * Retrieves all the records in billingdetails table.
	 * METHOD = Get.
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/billingdetails/getAll")
	public List<BillingDetails> getAll() {
		return null;

	}
	
	/*
	 * Saves data in the billingdetails table.
	 * METHOD = Post.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId of the new record.
	 */
	@PostMapping("/billingdetails/save")
	public String postData() {
		return null;
	}
	
	/*
	 * Updates data in the billingdetails table.
	 * METHOD = Put.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId of the updated record.
	 */
	@PutMapping("/billingdetails/update")
	public String update() {
		return null;
	}
	/*
	 * Deletes data in the billingdetails table.
	 * METHOD = Delete.
	 * REQUEST = Object containing ProductID of record that should be deleted.
	 * RESPONSE = billID of the deleted record.
	 */
	@DeleteMapping("/billingdetails/delete")
	public String delete() {
		return null;
	}
}
