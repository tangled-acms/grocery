package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Product;
import com.acms.models.ProductRetailer;
import com.acms.models.ProductRetailerEmbeddedId;
import com.acms.repositories.ProductRetailerRepository;

@Service
@Transactional
public class ProductRetailerService {
	@Autowired
	private ProductRetailerRepository productRetailerRepository;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Function to retrieve all records in ProductRetailer Table
	 * 
	 * @return List of all records in ProductRetailer Table
	 */
	public List<ProductRetailer> getAllProductRetailerDetails() {
		return this.productRetailerRepository.findAll();

	}
	
	/**
	 * Function to retrieve a single record in ProductRetailer Table
	 * 
	 * @param embeddedId
	 *            Contains the composite key pair for a single record
	 * @return Object with given ID of all records in ProductRetailer Table
	 * @throws ResourceNotFoundException
	 */
	public ProductRetailer getByEmbeddedId(ProductRetailerEmbeddedId embeddedId) throws ResourceNotFoundException {
		return this.productRetailerRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Entry with ID " + embeddedId.toString() + " not found!"));

	}
	
	/**
	 * Function to retrieve a list of records in ProductRetailer Table
	 * 
	 * @param productId
	 *            A specific product Id
	 * @return List of objects with given ID from ProductRetailer table
	 * @throws ResourceNotFoundException
	 */
	public List<ProductRetailer> getByProductId(String productId) throws ResourceNotFoundException {
		List<ProductRetailer> productRetailerDetails = this.productRetailerRepository
				.findByProductRetailerEmbeddedIdProductId(productId);
		return productRetailerDetails;

	}
	
	/**
	 * Function to retrieve a list of records in ProductRetailer Table
	 * 
	 * @param retailerId
	 *            A specific retailer Id
	 * @return List of objects with given ID from ProductRetailer table
	 * @throws ResourceNotFoundException
	 */
	public List<ProductRetailer> getByRetailerId(String retailerId) {
		List<ProductRetailer> productRetailerDetails = this.productRetailerRepository
				.findByProductRetailerEmbeddedIdRetailerId(retailerId);
		return productRetailerDetails;
	}
	
	/**
	 * Function to save an object to ProductRetailer Table 
	 * Additional Functionality -
	 * Increments quantity from Product table every time an item is bought
	 * 
	 * @param productRetailerDetails
	 *            An object with details of ProductRetailer Table
	 * @return Object that is saved
	 * @throws ResourceNotFoundException
	 */
	public ProductRetailer postDataToProductRetailerTable(ProductRetailer productRetailerDetails) throws ResourceNotFoundException {
		String productId = productRetailerDetails.getProductRetailerEmbeddedId().getProductId();
		Product product = productService.getByProductId(productId);
		int quantity = product.getQuantity();
		product.setQuantity((quantity+productRetailerDetails.getQuantity()));
		productService.updateProductDetails(productId, product);
		return this.productRetailerRepository.save(productRetailerDetails);
	}
	
	/**
	 * Function to Update an object in ProductRetailer Table
	 * 
	 * @param productRetailerDetails
	 *            An object with new details of ProductRetailer Table
	 * @return Object that is updated and saved
	 * @throws ResourceNotFoundException
	 */
	public ProductRetailer updateProductRetailerRecord(ProductRetailer productRetailerDetails) throws ResourceNotFoundException {
		ProductRetailerEmbeddedId embeddedId = productRetailerDetails.getProductRetailerEmbeddedId();
		ProductRetailer objectToUpdate = this.productRetailerRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Entry with ID " + embeddedId.toString() + " not found!"));
		objectToUpdate.setQuantity(productRetailerDetails.getQuantity());
		objectToUpdate.setCost(productRetailerDetails.getCost());
		return this.productRetailerRepository.save(objectToUpdate);
	}
	
	/**
	 * Function to delete an object in ProductRetailer Table
	 * 
	 * @param embeddedId
	 *            Contains the composite key pair for a single record
	 * @return emeddedId in String form
	 * @throws ResourceNotFoundException
	 */
	public String deleteProductRetailerRecord(ProductRetailerEmbeddedId embeddedId) throws ResourceNotFoundException {
		ProductRetailer objectToDelete = this.productRetailerRepository.findById(embeddedId).orElseThrow(
				() -> new ResourceNotFoundException("Bill with ID " + embeddedId.toString() + " not found!"));
		this.productRetailerRepository.delete(objectToDelete);
		return embeddedId.toString();

	}
}
