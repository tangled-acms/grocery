package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acms.models.Retailer;
import com.acms.services.RetailerService;

@RestController
public class RetailerController {
	
	@Autowired
	RetailerService retailerService;
	
	@GetMapping("/retailer/getAll")
	public List<Retailer> getAll(){
		
		return retailerService.getAllRetailers();
		
	}

	
	
}
