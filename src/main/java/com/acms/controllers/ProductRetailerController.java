package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.ProductRetailer;
import com.acms.models.ProductRetailerEmbeddedId;
import com.acms.services.ProductRetailerService;

@RestController
public class ProductRetailerController {

	@Autowired
	private ProductRetailerService productRetailerService;

	/**
	 * Retrieves all the records in productretailer table. METHOD = Get.
	 * 
	 * @return List of all the records.
	 */
	@GetMapping("/productretailer/getAll")
	public List<ProductRetailer> getAllProductRetailerDetails() {
		System.out.println(this.productRetailerService.getAllProductRetailerDetails());
		return this.productRetailerService.getAllProductRetailerDetails();

	}

	/**
	 * Retrieves the records in productretailer table with the given productId.
	 * METHOD = Get.
	 * 
	 * @param productId
	 *            Id of the requried record.
	 * @return List of all the records which contain that productId.
	 * @throws ResourceNotFoundException
	 *             If given Id does not exist
	 */
	@GetMapping("/productretailer/getByProductRetailerId")
	public List<ProductRetailer> getByProductId(@RequestBody ProductRetailerEmbeddedId embeddedId)
			throws ResourceNotFoundException {
		if (embeddedId.getProductId() != null)
			return this.productRetailerService.getByProductId(embeddedId.getProductId());
		else
			return this.productRetailerService.getByRetailerId(embeddedId.getRetailerId());
	}

	/**
	 * Retrieves the records in productretailer table with the given composite Id.
	 * METHOD = Get.
	 * 
	 * @param embeddedId
	 *            Object containing both productId and retailerId.
	 * @return Object with that Id.
	 * @throws ResourceNotFoundException
	 *             If given Id does not exist
	 */
	@GetMapping("/productretailer/getById")
	public ProductRetailer getByEmbeddedId(@RequestBody ProductRetailerEmbeddedId embeddedId)
			throws ResourceNotFoundException {
		return this.productRetailerService.getByEmbeddedId(embeddedId);

	}

	/**
	 * Saves data in the productretailer table. METHOD = Post.
	 * 
	 * @param productRetailerDetails
	 *            Object containing all the details of the attributes.
	 * @return Object of the saved record.
	 * @throws ResourceNotFoundException
	 *             If given Id does not exist
	 */
	@PostMapping("/productretailer/save")
	public ProductRetailer postDataToProductRetailerTable(@RequestBody ProductRetailer productRetailerDetails)
			throws ResourceNotFoundException {
		return this.productRetailerService.postDataToProductRetailerTable(productRetailerDetails);
	}

	/**
	 * Updates data in the productretailer table. METHOD = Put.
	 * 
	 * @param productRetailerDetails
	 *            Object containing all the details of the attributes.
	 * @return Object of the updated record.
	 * @throws ResourceNotFoundException
	 *             If given Id does not exist
	 */
	@PutMapping("/productretailer/update")
	public ProductRetailer updateProductRetailerRecord(@RequestBody ProductRetailer productRetailerDetails)
			throws ResourceNotFoundException {
		return this.productRetailerService.updateProductRetailerRecord(productRetailerDetails);
	}

	/**
	 * Deletes data in the productretailer table. METHOD = Delete.
	 * 
	 * @param embeddedId
	 *            Object containing ProductID of record that should be deleted.
	 * @return productId and retailerId of the deleted record.
	 * @throws ResourceNotFoundException
	 *             If given Id does not exist
	 */
	@DeleteMapping("/productretailer/delete")
	public String deleteProductRetailerRecord(@RequestBody ProductRetailerEmbeddedId embeddedId)
			throws ResourceNotFoundException {
		return this.productRetailerService.deleteProductRetailerRecord(embeddedId);
	}
}
