package com.acms.services;

import java.util.List;

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

	public List<Retailer> getAllRetailerDetails() {

		return this.retailerRepository.findAll();

	}

	public Retailer getByRetailerId(String retailerId) throws ResourceNotFoundException {
		Retailer retailer = this.retailerRepository.findById(retailerId)
				.orElseThrow(() -> new ResourceNotFoundException("Retailer with ID " + retailerId + " not found!"));
		return retailer;

	}

	public Retailer postDataToRetailerTable(Retailer retailer) {
		return this.retailerRepository.save(retailer);
	}

	public Retailer updateRetailerDetails(String retailerId, Retailer retailerdetails) throws ResourceNotFoundException {
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

	public String deleteRetailerReecord(String retailerId) throws ResourceNotFoundException {
		Retailer retailer = this.retailerRepository.findById(retailerId)
				.orElseThrow(() -> new ResourceNotFoundException("Retailer with ID " + retailerId + " not found!"));
		this.retailerRepository.delete(retailer);
		return retailerId;

	}

}
