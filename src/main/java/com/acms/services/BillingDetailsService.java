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
	BillingDetailsRepository billingDetailsRepository;

	public List<BillingDetails> getAll() {
		return this.billingDetailsRepository.findAll();

	}
	
	public BillingDetails getByID(BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException {
		return billingDetailsRepository.findById(embeddedId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + embeddedId + " not found!"));
		
	}

	public List<BillingDetails> getByBillId(int billId) throws ResourceNotFoundException{
		List<BillingDetails> billingDetails = billingDetailsRepository.findByBillingDetailsEmbeddedIdBillId(billId);
		return billingDetails;

	}

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
