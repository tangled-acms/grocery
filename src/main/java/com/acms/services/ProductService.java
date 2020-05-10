package com.acms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Product;
import com.acms.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Function to retrieve all records in Product Table
	 * 
	 * @return List of all records in Product Table
	 */
	public List<Product> getAllProductDetails() {
		return this.productRepository.findAll();

	}

	/**
	 * Function to retrieve all records in Product Table
	 * 
	 * @return List of all records in Product Table
	 */
	public List<Product> getAllAvailableProductDetails() {
		List<Product> available = new ArrayList<Product>();
		List<Product> product = productRepository.findAll();

		for (Product prod : product) {
			if (prod.getQuantity() > 0)
				available.add(prod);
		}
		return available;

	}

	/**
	 * Function to retrieve a single record in Product Table
	 * 
	 * @param productId
	 *            Id of the required record
	 * @return Object with given ID of all records in Product Table
	 * @throws ResourceNotFoundException
	 */
	public Product getByProductId(String productId) throws ResourceNotFoundException {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		return product;

	}

	/**
	 * Function to save an object to Product Table
	 * 
	 * @param product
	 *            An object with details of Product Table
	 * @return Object that is saved
	 */
	public Product postDataToProductTable(Product product) {
		return this.productRepository.save(product);
	}

	/**
	 * Function to Update an object in Product Table
	 * 
	 * @param productId
	 *            Id of the required record
	 * @param productdetails
	 *            An object with new details of Product Table
	 * @return Object that is updated and saved
	 * @throws ResourceNotFoundException
	 */
	public Product updateProductDetails(String productId, Product productdetails) throws ResourceNotFoundException {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		product.setDescription(productdetails.getDescription());
		product.setName(productdetails.getName());
		product.setQuantity(productdetails.getQuantity());
		product.setMRP(productdetails.getMRP());
		product.setPromotion(productdetails.getPromotion());
		return this.productRepository.save(product);
	}

	/**
	 * Function to delete an object in Product Table
	 * 
	 * @param productId
	 *            Id of the required record
	 * @return productId of deleted record
	 * @throws ResourceNotFoundException
	 */
	public String deleteProductRecord(String productId) throws ResourceNotFoundException {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		this.productRepository.delete(product);
		return productId;

	}

	/**
	 * Function to send scheduled notification every 24 hours of which products are
	 * low in quantity
	 * 
	 * @param cron
	 *            gives notification once at 10:30am every Monday to Sunday
	 */
	@Scheduled(cron = "0 30 10 * * 1-7")
	void lowOnProduct() {
		List<Product> productList = productRepository.findAll();
		Predicate<Product> quantity = product -> product.getQuantity() < 5;
		List<Product> result = productList.stream().filter(quantity).collect(Collectors.toList());
		System.out.print(result.toString());
	}
}
