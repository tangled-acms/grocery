package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Billing;
import com.acms.repositories.BillingRepository;

@Service
@Transactional
public class BillingService {

	@Autowired
	BillingRepository billingRepository;

	public List<Billing> getAll() {
		return this.billingRepository.findAll();
		
	}

	public Billing getById(int billId) throws ResourceNotFoundException {
		Billing billing = billingRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("Bill with ID " + billId + " not found!"));
		return billing;

	}

	public Billing postData(Billing billing) {
		return this.billingRepository.save(billing);
	}

	public Billing update(int billId, Billing billingdetails) throws ResourceNotFoundException {
		Billing billing = billingRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("Bill with ID " + billId + " not found!"));
		billing.setSubTotal(billingdetails.getSubTotal());
		billing.setPaymentMode(billingdetails.getPaymentMode());
		return this.billingRepository.save(billing);
	}

	public int delete(int billId) throws ResourceNotFoundException {
		Billing billing = billingRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + billId + " not found!"));
		this.billingRepository.delete(billing);
		return billId;

	}
}
