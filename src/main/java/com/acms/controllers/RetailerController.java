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
import com.acms.models.Retailer;
import com.acms.services.RetailerService;

@RestController
public class RetailerController {

	@Autowired
	RetailerService retailerService;

	/*
	 * Retrieves all the records in retailer table. 
	 * METHOD = Get. 
	 * REQUEST = null.
	 * RESPONSE = List of all the records.
	 */
	@GetMapping("/retailer/getAll")
	public List<Retailer> getAll() {
		return retailerService.getAll();

	}

	/*
	 * Retrieves one record from retailer table. 
	 * METHOD = Get. 
	 * REQUEST = retailerID of the required details. 
	 * RESPONSE = Object containing all the details of that retailer.
	 */
	@GetMapping("/retailer/getById/{id}")
	public Retailer getById(@PathVariable(value = "id") String retailerId) throws ResourceNotFoundException {
		return retailerService.getById(retailerId);
	}

	/*
	 * Saves data in the retailer table. 
	 * METHOD = Post. 
	 * REQUEST = Object containing all the details of the attributes. 
	 * RESPONSE = retailerID of the new record.
	 */
	@PostMapping("/retailer/save")
	public Retailer postData(@RequestBody Retailer retailer) {
		return retailerService.postData(retailer);
	}

	/*
	 * Updates data in the retailer table. 
	 * METHOD = Put. 
	 * REQUEST = Object containing all the details of the attributes. 
	 * RESPONSE = retailerID of the updated record.
	 */
	@PutMapping("/retailer/update/{id}")
	public Retailer update(@PathVariable(value = "id") String retailerId, @Valid @RequestBody Retailer retailerDetails)
			throws ResourceNotFoundException {
		return retailerService.update(retailerId, retailerDetails);
	}

	/*
	 * Deletes data in the retailer table. 
	 * METHOD = Delete 
	 * REQUEST = Object containing retailerID of record that should be deleted. 
	 * RESPONSE = retailerID of the deleted record.
	 */
	@DeleteMapping("/retailer/delete/{id}")
	public String delete(@PathVariable(value = "id") String retailerId) throws ResourceNotFoundException {
		return retailerService.delete(retailerId);
	}

}
