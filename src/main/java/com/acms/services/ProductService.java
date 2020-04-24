package com.acms.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.acms.models.Product;
import com.acms.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {

		return this.productRepository.findAll();

	}

	public Product getById(String productId) {
		return null;

	}

	public Product getByName() {
		return null;
	}

	public Product postData(Product product) {
		return this.productRepository.save(product);
	}
	
	public Product update(String productId, Product productdetails) {
		Product product=productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with ID "+productId+" not found!"));
		product.setDescription(productdetails.getDescription());
		product.setName(productdetails.getName());
		product.setQuantity(productdetails.getQuantity());
		product.setMRP(productdetails.getMRP());
		product.setPromotion(productdetails.getPromotion());
		return this.productRepository.save(product);
	}
}
