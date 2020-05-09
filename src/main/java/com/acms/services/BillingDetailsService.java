package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.BillingDetails;
import com.acms.models.BillingDetailsEmbeddedId;
import com.acms.models.Product;
import com.acms.repositories.BillingDetailsRepository;

@Service
@Transactional
public class BillingDetailsService {
	
	@Autowired
	private BillingDetailsRepository billingDetailsRepository;

	@Autowired
	private ProductService productService;

	/**
	 * Function to retrieve all records in Billing Details Table
	 * 
	 * @return List of all records in Billing Details Table
	 */
	public List<BillingDetails> getAllBillingDetails() {
		return this.billingDetailsRepository.findAll();

	}

	/**
	 * Function to retrieve a single record in Billing Details Table
	 * 
	 * @param embeddedId
	 *            Contains the composite key pair for a single record
	 * @return Object with given ID of all records in Billing Details Table
	 * @throws ResourceNotFoundException
	 */
	public BillingDetails getByEmbeddedBillId(BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException {
		return this.billingDetailsRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));

	}

	/**
	 * Function to retrieve a list of records in Billing Details Table
	 * 
	 * @param billId
	 *            A specific bill Id
	 * @return List of objects with given ID from Billing Details table
	 * @throws ResourceNotFoundException
	 */
	public List<BillingDetails> getByBillId(int billId) throws ResourceNotFoundException {
		List<BillingDetails> billingDetails = this.billingDetailsRepository
				.findByBillingDetailsEmbeddedIdBillId(billId);
		return billingDetails;

	}

	/**
	 * Function to save an object to Billing Details Table 
	 * Additional Functionality -
	 * Decrements quantity from Product table every time an item is bought
	 * Automatically computes cost field
	 * 
	 * @param billingDetails
	 *            An object with details of Billing Details Table
	 * @return Object that is saved
	 * @throws ResourceNotFoundException
	 */
	public BillingDetails postDataToBillingDetails(BillingDetails billingDetails) throws ResourceNotFoundException {
		String productId = billingDetails.getBillingDetailsEmbeddedId().getProductId();
		Product product = productService.getByProductId(productId);
		billingDetails.setCost(((product.getMRP()) - ((product.getPromotion() / 100) * product.getMRP()))
				* (billingDetails.getQuantity()));
		int quantity = product.getQuantity();
		product.setQuantity((quantity - billingDetails.getQuantity()));
		productService.updateProductDetails(productId, product);
		if(AnalyticsService.map.containsKey(productId))
		{
			AnalyticsService.map.put(productId,AnalyticsService.map.get(productId)+billingDetails.getQuantity());
		}
		else {
			AnalyticsService.map.put(productId,quantity - billingDetails.getQuantity());
		}
		return this.billingDetailsRepository.save(billingDetails);
	}

	/**
	 * Function to Update an object in Billing Details Table
	 * 
	 * @param billingDetails
	 *            An object with new details of Billing Details Table
	 * @return Object that is updated and saved
	 * @throws ResourceNotFoundException
	 */
	public BillingDetails updateBillingDetailsRecord(BillingDetails billingDetails) throws ResourceNotFoundException {
		BillingDetailsEmbeddedId embeddedId = billingDetails.getBillingDetailsEmbeddedId();
		BillingDetails objectToUpdate = this.billingDetailsRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));
		objectToUpdate.setQuantity(billingDetails.getQuantity());
		objectToUpdate.setCost(billingDetails.getCost());
		return this.billingDetailsRepository.save(objectToUpdate);
	}

	/**
	 * Function to delete an object in Billing Details Table
	 * Additional Functionality-
	 * Increases product quantity when any billing detail is deleted.
	 * 
	 * @param embeddedId
	 *            Contains the composite key pair for a single record
	 * @return emeddedId in String form
	 * @throws ResourceNotFoundException
	 */
	public String deleteBillingDetailsRecord(BillingDetailsEmbeddedId embeddedId) throws ResourceNotFoundException {
		String productId = embeddedId.getProductId();
		Product product = productService.getByProductId(productId);
		BillingDetails objectToDelete = this.billingDetailsRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));
		product.setQuantity(product.getQuantity()+objectToDelete.getQuantity());
		productService.updateProductDetails(productId, product);
		if(AnalyticsService.map.containsKey(productId))
		{
			AnalyticsService.map.put(productId,AnalyticsService.map.get(productId)-objectToDelete.getQuantity());
		}
		this.billingDetailsRepository.delete(objectToDelete);
		return embeddedId.toString();

	}
}
