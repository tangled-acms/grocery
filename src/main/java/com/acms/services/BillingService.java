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
	private BillingRepository billingRepository;

	/**
	 * Function to retrieve all records in Billing Table
	 * 
	 * @return List of all records in Billing Table
	 */
	public List<Billing> getAllBillingRecords() {
		return this.billingRepository.findAll();

	}

	/**
	 * Function to retrieve a single record in Billing Table
	 * 
	 * @param billId
	 *            Id of the record to be updated
	 * @return Object with given ID of all records in Billing Table
	 * @throws ResourceNotFoundException
	 */
	public Billing getByBillId(int billId) throws ResourceNotFoundException {
		Billing billing = this.billingRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("Bill with ID " + billId + " not found!"));
		return billing;

	}

	/**
	 * Function to save an object to Billing Table
	 * 
	 * @param billing
	 *            An object with details of Billing Table
	 * @return Object that is saved
	 */
	public Billing postDataToBillingTable(Billing billing) {
		return this.billingRepository.save(billing);
	}

	/**
	 * Function to Update an object in Billing Table
	 * 
	 * @param billId
	 *            BillId of the object that needs to be updated
	 * @param billingDetails
	 *            An object with new details of Billing Table
	 * @return Object that is updated and saved
	 * @throws ResourceNotFoundException
	 */
	public Billing updateBillingRecord(int billId, Billing billingdetails) throws ResourceNotFoundException {
		Billing billing = this.billingRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("Bill with ID " + billId + " not found!"));
		billing.setSubTotal(billingdetails.getSubTotal());
		billing.setPaymentMode(billingdetails.getPaymentMode());
		return this.billingRepository.save(billing);
	}

	/**
	 * Function to delete an object in Billing Table
	 * 
	 * @param billId
	 *            BillId of the object that needs to be updated
	 * @return billId in String form
	 * @throws ResourceNotFoundException
	 */
	public int deleteBillingRecord(int billId) throws ResourceNotFoundException {
		Billing billing = this.billingRepository.findById(billId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + billId + " not found!"));
		this.billingRepository.delete(billing);
		return billId;

	}
}
