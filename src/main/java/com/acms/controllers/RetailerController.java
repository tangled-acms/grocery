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
	private RetailerService retailerService;

	/**
	 * Retrieves all the records in retailer table. 
	 * METHOD = Get. 
	 * @param null.
	 * @return List of all the records.
	 */
	@GetMapping("/retailer/getAll")
	public List<Retailer> getAllRetailerDetails() {
		return this.retailerService.getAllRetailerDetails();

	}

	/**
	 * Retrieves one record from retailer table. 
	 * METHOD = Get. 
	 * @param retailerID of the required details. 
	 * @return Object containing all the details of that retailer.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@GetMapping("/retailer/getById/{id}")
	public Retailer getByRetailerId(@PathVariable(value = "id") String retailerId) throws ResourceNotFoundException {
		return this.retailerService.getByRetailerId(retailerId);
	}

	/*
	 * Saves data in the retailer table. 
	 * METHOD = Post. 
	 * @param Object containing all the details of the attributes. 
	 * @return retailerID of the new record.
	 */
	@PostMapping("/retailer/save")
	public Retailer postDataToRetailerTable(@RequestBody Retailer retailer) {
		return this.retailerService.postDataToRetailerTable(retailer);
	}

	/**
	 * Updates data in the retailer table. 
	 * METHOD = Put. 
	 * @param Object containing all the details of the attributes. 
	 * @return retailerID of the updated record.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@PutMapping("/retailer/update/{id}")
	public Retailer updateRetailerDetails(@PathVariable(value = "id") String retailerId, @Valid @RequestBody Retailer retailerDetails)
			throws ResourceNotFoundException {
		return this.retailerService.updateRetailerDetails(retailerId, retailerDetails);
	}

	/**
	 * Deletes data in the retailer table. 
	 * METHOD = Delete 
	 * @param Object containing retailerID of record that should be deleted. 
	 * @return retailerID of the deleted record.
	 * @exception throws user defined exception ResourceNotFoundException if given Id does not exist
	 */
	@DeleteMapping("/retailer/delete/{id}")
	public String deleteRetailerReecord(@PathVariable(value = "id") String retailerId) throws ResourceNotFoundException {
		return this.retailerService.deleteRetailerReecord(retailerId);
	}

}
