package com.acms.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acms.models.Analytics;
import com.acms.models.AnalyticsEmbeddedId;
import com.acms.repositories.AnalyticsRepository;

@Service
@Transactional
public class AnalyticsService {
	
	protected static Map<String, Integer> map = new HashMap<String, Integer>();
			
	@Autowired
	private AnalyticsRepository analyticsRepository;

	/**
	 * Function to retrieve all records in Analytics Table
	 * 
	 * @return List of all records in Analytics Table
	 */
	public List<Analytics> getAllAnalyticsRecords() {
		return this.analyticsRepository.findAll();

	}
	
	/**
	 * Function to retrieve a list of records in Analytics Table
	 * 
	 * @param productId
	 *            Id of the product
	 * @return Object with given ID of all records in Billing Table
	 */
	public List<Analytics> getByProductId(int productId) {
		List<Analytics> analytics =  this.analyticsRepository.findByAnalyticsEmbeddedIdProductId(productId);
		return analytics;

	}
	
	/**
	 * Function to save the HashMap to Analytics Table
	 * 
	 * @return String for successful entry
	 */
	public String postDataToAnalytics() {
		for(String i : map.keySet()){
			int sold = map.get(i);
			
			AnalyticsEmbeddedId analyticsId = new AnalyticsEmbeddedId();
			analyticsId.setProductId(i);
			analyticsId.setTimeStamp(Instant.now().getEpochSecond());
			
			Analytics analytics=new Analytics();
			analytics.setAnalyticsEmbeddedId(analyticsId);
			analytics.setSold(sold);
			analyticsRepository.save(analytics);
		}
		map.clear();
		return "Successful Entries";
		
	}
	
	/**
	 * Function to save to the Analytics table everyday at 7:30pm 
	 * 
	 * @param cron
	 *            gives notification once at 10:30am every Monday to Sunday
	 */
	@Scheduled(cron = "0 30 19 * * 1-7")
	void periodicSave() {
		postDataToAnalytics();
	}

}