package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.BillingDetails;
import com.acms.models.BillingDetailsEmbeddedId;
import com.acms.repositories.BillingDetailsRepository;

@Service
@Transactional
public class BillingDetailsService {
	@Autowired
	private BillingDetailsRepository billingDetailsRepository;

	public List<BillingDetails> getAllBillingDetails() {
		return this.billingDetailsRepository.findAll();

	}

	public BillingDetails getByEmbeddedBillId(BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException {
		return this.billingDetailsRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));

	}

	public List<BillingDetails> getByBillId(int billId) throws ResourceNotFoundException {
		List<BillingDetails> billingDetails = this.billingDetailsRepository.findByBillingDetailsEmbeddedIdBillId(billId);
		return billingDetails;

	}

	public BillingDetails postDataToBillingDetails(BillingDetails billingDetails) {
		return this.billingDetailsRepository.save(billingDetails);
	}

	public BillingDetails updateBillingDetailsRecord(BillingDetails billingDetails) throws ResourceNotFoundException {
		BillingDetailsEmbeddedId embeddedId = billingDetails.getBillingDetailsEmbeddedId();
		BillingDetails objectToUpdate = this.billingDetailsRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));
		objectToUpdate.setQuantity(billingDetails.getQuantity());
		objectToUpdate.setCost(billingDetails.getCost());
		return this.billingDetailsRepository.save(objectToUpdate);
	}

	public String deleteBillingDetailsRecord(BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException {
		BillingDetails objectToDelete = this.billingDetailsRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));
		this.billingDetailsRepository.delete(objectToDelete);
		return embeddedId.toString();

	}
}
