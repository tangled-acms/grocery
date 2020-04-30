package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.ProductRetailer;
import com.acms.models.ProductRetailerEmbeddedId;
import com.acms.repositories.ProductRetailerRepository;

@Service
@Transactional
public class ProductRetailerService {
	@Autowired
	private ProductRetailerRepository productRetailerRepository;

	public List<ProductRetailer> getAllProductRetailerDetails() {
		return this.productRetailerRepository.findAll();

	}

	public ProductRetailer getByEmbeddedId(ProductRetailerEmbeddedId embeddedId) throws ResourceNotFoundException {
		return this.productRetailerRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Entry with ID " + embeddedId.toString() + " not found!"));

	}

	public List<ProductRetailer> getByProductId(String productId) throws ResourceNotFoundException {
		List<ProductRetailer> productRetailerDetails = this.productRetailerRepository
				.findByProductRetailerEmbeddedIdProductId(productId);
		return productRetailerDetails;

	}

	public ProductRetailer postDataToProductRetailerTable(ProductRetailer productRetailerDetails) {
		return this.productRetailerRepository.save(productRetailerDetails);
	}

	public ProductRetailer updateProductRetailerRecord(ProductRetailer productRetailerDetails) throws ResourceNotFoundException {
		ProductRetailerEmbeddedId embeddedId = productRetailerDetails.getProductRetailerEmbeddedId();
		ProductRetailer objectToUpdate = this.productRetailerRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Entry with ID " + embeddedId.toString() + " not found!"));
		objectToUpdate.setQuantity(productRetailerDetails.getQuantity());
		objectToUpdate.setCost(productRetailerDetails.getCost());
		return this.productRetailerRepository.save(objectToUpdate);
	}

	public String deleteProductRetailerRecord(ProductRetailerEmbeddedId embeddedId) throws ResourceNotFoundException {
		ProductRetailer objectToDelete = this.productRetailerRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));
		this.productRetailerRepository.delete(objectToDelete);
		return embeddedId.toString();

	}
}
