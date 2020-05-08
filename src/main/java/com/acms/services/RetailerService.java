package com.acms.services;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Retailer;
import com.acms.repositories.RetailerRepository;

@Service
@Transactional
public class RetailerService {

	@Autowired
	private RetailerRepository retailerRepository;

	/**
	 * Function to retrieve all records in Retailer Table
	 * 
	 * @return List of all records in Retailer Table
	 */
	public List<Retailer> getAllRetailerDetails() {
		return this.retailerRepository.findAll();

	}
	
	public List<Retailer> getAllActiveRetailerDetails() {
		List<Retailer> retailerList = retailerRepository.findAll();
		Predicate<Retailer> activeStatus= retailer -> retailer.getActivestatus() == 1;
		List<Retailer> result = retailerList.stream().filter(activeStatus).collect(Collectors.toList());
		return result;

	}
	/**
	 * Function to retrieve a single record in Retailer Table
	 * 
	 * @param retailerId
	 *            Id of the required record
	 * @return Object with given ID of all records in Retailer Table
	 * @throws ResourceNotFoundException
	 */
	public Retailer getByRetailerId(String retailerId) throws ResourceNotFoundException {
		Retailer retailer = this.retailerRepository.findById(retailerId)
				.orElseThrow(() -> new ResourceNotFoundException("Retailer with ID " + retailerId + " not found!"));
		return retailer;

	}

	/**
	 * Function to save an object to Retailer Table
	 * 
	 * @param retailer
	 *            An object with details of Retailer Table
	 * @return Object that is saved
	 */
	public Retailer postDataToRetailerTable(Retailer retailer) {
		return this.retailerRepository.save(retailer);
	}

	/**
	 * Function to Update an object in Retailer Table
	 * 
	 * @param retailerId
	 *            Id of the required record
	 * @param retailerdetails
	 *            An object with new details of Retailer Table
	 * @return Object that is updated and saved
	 * @throws ResourceNotFoundException
	 */
	public Retailer updateRetailerDetails(String retailerId, Retailer retailerdetails)
			throws ResourceNotFoundException {
		Retailer retailer = this.retailerRepository.findById(retailerId)
				.orElseThrow(() -> new ResourceNotFoundException("Retailer with ID " + retailerId + " not found!"));
		retailer.setName(retailerdetails.getName());
		retailer.setAddress1(retailerdetails.getAddress1());
		retailer.setAddress2(retailerdetails.getAddress2());
		retailer.setAddress3(retailerdetails.getAddress3());
		retailer.setContact1(retailerdetails.getContact1());
		retailer.setContact2(retailerdetails.getContact2());
		retailer.setContact3(retailerdetails.getContact3());
		return this.retailerRepository.save(retailer);
	}

	/**
	 * Function to mark retailer as inactive Retailer Table
	 * 
	 * @param retailerId
	 *            Id of the required record
	 * @return retailerId of deleted record
	 * @throws ResourceNotFoundException
	 */
	public String deleteRetailerReecord(String retailerId) throws ResourceNotFoundException {
		Retailer retailer = this.retailerRepository.findById(retailerId)
				.orElseThrow(() -> new ResourceNotFoundException("Retailer with ID " + retailerId + " not found!"));
		retailer.setActivestatus(0);
		updateRetailerDetails(retailerId,retailer);
		return retailerId;

	}

}
