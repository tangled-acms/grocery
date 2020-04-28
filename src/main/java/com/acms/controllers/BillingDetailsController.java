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
import com.acms.models.BillingDetails;
import com.acms.models.BillingDetailsEmbeddedId;
import com.acms.services.BillingDetailsService;


@RestController
public class BillingDetailsController {
	
	@Autowired
	private BillingDetailsService billingDetailsService;
	
	/*
	 * Retrieves all the records in billingdetails table.
	 * METHOD = Get.
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/billingdetails/getAll")
	public List<BillingDetails> getAll() {
		return billingDetailsService.getAll();

	}
	
	/*
	 * Retrieves the records in billingdetails table with the given productId.
	 * METHOD = Get.
	 * REQUEST = billId.
	 * RESPONSE = List of all the records which contain that billId.
	 */
	@GetMapping("/billingdetails/getById/{id}")
	public List<BillingDetails> getByBillId(@PathVariable(value = "id") int billId) throws ResourceNotFoundException {
		return billingDetailsService.getByBillId(billId);

	}
	
	/*
	 * Retrieves the records in billingdetails table with the given composite Id.
	 * METHOD = Get.
	 * REQUEST = Object containing both billId and productId.
	 * RESPONSE = Object with that Id.
	 */
	@GetMapping("/billingdetails/getById")
	public BillingDetails getById(@RequestBody BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException { 
		return billingDetailsService.getByID(embeddedId);

	}
	
	/*
	 * Saves data in the billingdetails table.
	 * METHOD = Post.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = billId of the new record.
	 */
	@PostMapping("/billingdetails/save")
	public BillingDetails postData(@RequestBody BillingDetails billingDetails) {
		return billingDetailsService.postData(billingDetails);
	}
	
	/*
	 * Updates data in the billingdetails table.
	 * METHOD = Put.
	 * REQUEST = Object containing all the details of the attributes.
	 * RESPONSE = Object of the updated record.
	 */
	@PutMapping("/billingdetails/update")
	public BillingDetails update(@RequestBody BillingDetails billingDetails) throws ResourceNotFoundException {
		return billingDetailsService.update(billingDetails);
	}
	/*
	 * Deletes data in the billingdetails table.
	 * METHOD = Delete.
	 * REQUEST = Object containing ProductID of record that should be deleted.
	 * RESPONSE = billID of the deleted record.
	 */
	@DeleteMapping("/billingdetails/delete")
	public String delete(@RequestBody BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException {
		return billingDetailsService.delete(embeddedId);
	}
}
