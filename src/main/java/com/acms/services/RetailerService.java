package com.acms.services;

import java.util.ArrayList;
import java.util.List;
//import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.acms.models.Retailer;

@Service
public class RetailerService {
	
	public List<Retailer> getAllRetailers() {
		
		List<Retailer> listOfRetailers= new ArrayList<>();
		listOfRetailers.add(new Retailer(1,"101A","nestle","#304, Peenya, b'lore","","",98,0,0));
		listOfRetailers.add(new Retailer(2,"102A","nandhini","#305, Peenya, b'lore","","",98,0,0));
		listOfRetailers.add(new Retailer(3,"103A","pepsi","#306, Peenya, b'lore","","",98,0,0));
		return listOfRetailers;
		
	}
}
