package com.acms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Product;
import com.acms.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Retrieves all the records in product table. 
	 * METHOD = Get. 
	 * @param null.
	 * @return List of objects of Product type.
	 */
	@GetMapping("/product/getAll")
	public List<Product> getAllProductDetails() {
		return this.productService.getAllProductDetails();

	}

	/**
	 * Retrieves one record from product table. 
	 * METHOD = Get. 
	 * @param Object containing productID. 
	 * @return Object containing all the details of that product.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@GetMapping("/product/getById/{id}")
	public Product getByProductId(@PathVariable(value = "id") String productId) throws ResourceNotFoundException {
		return this.productService.getByProductId(productId);

	}

	/**
	 * Saves data in the product table. 
	 * METHOD = Post. 
	 * @param Object containing all the details of the attributes. 
	 * @return Object containing all the details of the saved record.
	 */
	@PostMapping("/product/save")
	public Product postDataToProductTable(@RequestBody Product product) {
		return this.productService.postDataToProductTable(product);
	}

	/**
	 * Updates data in the product table. 
	 * METHOD = Put. 
	 * @param Object containing all the details of the attributes. 
	 * @return Object containing all the details of the updated record.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@PutMapping("/product/update/{id}")
	public Product updateProductDetails(@PathVariable(value = "id") String productId, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		return this.productService.updateProductDetails(productId, productDetails);

	}

	/**
	 * Deletes data in the product table. 
	 * METHOD = Delete 
	 * @param Object containing productID of record that should be deleted. 
	 * @return productID of the deleted record.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@DeleteMapping("/product/delete/{id}")
	public String deleteProductRecord(@PathVariable(value = "id") String productId) throws ResourceNotFoundException {
		return this.productService.deleteProductRecord(productId);
	}
}
