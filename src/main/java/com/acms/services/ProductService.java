package com.acms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Product> getAllProductDetails() {
		return this.productRepository.findAll();

	}

	public Product getByProductId(String productId) throws ResourceNotFoundException {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		return product;

	}

	public Product postDataToProductTable(Product product) {
		return this.productRepository.save(product);
	}

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

	public String deleteProductRecord(String productId) throws ResourceNotFoundException {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found!"));
		this.productRepository.delete(product);
		return productId;

	}
}
