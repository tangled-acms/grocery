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

	/**
	 * Retrieves all the records in billingdetails table. METHOD = Get.
	 * 
	 * @param null.
	 * @return List of all the records.
	 */
	@GetMapping("/billingdetails/getAll")
	public List<BillingDetails> getAllBillingDetails() {
		return this.billingDetailsService.getAllBillingDetails();

	}

	/**
	 * Retrieves the records in billingdetails table with the given billId.
	 * METHOD = Get.
	 * 
	 * @param billId.
	 * @return List of all the records which contain that billId.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@GetMapping("/billingdetails/getById/{id}")
	public List<BillingDetails> getByBillId(@PathVariable(value = "id") int billId) throws ResourceNotFoundException {
		return this.billingDetailsService.getByBillId(billId);

	}

	/**
	 * Retrieves the records in billingdetails table with the given composite Id.
	 * METHOD = Get.
	 * 
	 * @param embeddedId
	 *            Object containing both billId and productId.
	 * @return Object with that Id.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@GetMapping("/billingdetails/getById")
	public BillingDetails getByEmbeddedBillId(@RequestBody BillingDetailsEmbeddedId embeddedId)
			throws ResourceNotFoundException {
		return this.billingDetailsService.getByEmbeddedBillId(embeddedId);

	}

	/**
	 * Saves data in the billingdetails table. METHOD = Post.
	 * 
	 * @param billingDetails
	 *            Object containing all the details of the attributes.
	 * @return billId of the new record.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@PostMapping("/billingdetails/save")
	public BillingDetails postDataToBillingDetails(@RequestBody BillingDetails billingDetails)
			throws ResourceNotFoundException {
		return this.billingDetailsService.postDataToBillingDetails(billingDetails);
	}

	/**
	 * Updates data in the billingdetails table. METHOD = Put.
	 * 
	 * @param billingDetails
	 *            Object containing all the details of the attributes.
	 * @return Object of the updated record.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@PutMapping("/billingdetails/update")
	public BillingDetails updateBillingDetailsRecord(@RequestBody BillingDetails billingDetails)
			throws ResourceNotFoundException {
		return this.billingDetailsService.updateBillingDetailsRecord(billingDetails);
	}

	/**
	 * Deletes data in the billingdetails table. METHOD = Delete.
	 * 
	 * @param embeddedId
	 *            Object containing both billId and productId
	 * @return billID of the deleted record.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@DeleteMapping("/billingdetails/delete")
	public String deleteBillingDetailsRecord(@RequestBody BillingDetailsEmbeddedId embeddedId)
			throws ResourceNotFoundException {
		return this.billingDetailsService.deleteBillingDetailsRecord(embeddedId);
	}
}
