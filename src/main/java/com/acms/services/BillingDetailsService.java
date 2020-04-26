package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.BillingDetails;
import com.acms.repositories.BillingDetailsRepository;

public class BillingDetailsService {
	@Autowired
	BillingDetailsRepository billingDetailsRepository;

	public List<BillingDetails> getAll() {

		return this.billingDetailsRepository.findAll();

	}

	/*public BillingDetails getById(int billId) throws ResourceNotFoundException{
		BillingDetails billingDetails = billingDetailsRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("billId with ID " + billId + " not found!"));
		return billingDetails;

	}*/

	public BillingDetails postData(BillingDetails billingDetails) {
		return this.billingDetailsRepository.save(billingDetails);
	}

	/*public BillingDetails update(int billId, BillingDetails billingDetailsObj) throws ResourceNotFoundException {
		Product product = billingDetailsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		product.setDescription(productdetails.getDescription());
		product.setName(productdetails.getName());
		product.setQuantity(productdetails.getQuantity());
		product.setMRP(productdetails.getMRP());
		product.setPromotion(productdetails.getPromotion());
		return this.billingDetailsRepository.save(product);
	}*/

	/*public String delete(String productId) throws ResourceNotFoundException{
		Product product = billingDetailsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		this.billingDetailsRepository.delete(product);
		return productId;

	}*/
}
