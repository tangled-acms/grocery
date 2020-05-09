package com.acms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acms.exceptions.ResourceNotFoundException;
import com.acms.models.Analytics;
import com.acms.services.AnalyticsService;

@RestController
public class AnayticsController {
	@Autowired
	private AnalyticsService analyticsService;
	
	/**
	 * Retrieves all the records in Analytics table. METHOD = Get.
	 * 
	 * @param null.
	 * @return List of all the records.
	 */
	@GetMapping("/analytics/getAll")
	public List<Analytics> getAllAnalytics() {
		return this.analyticsService.getAllAnalyticsRecords();

	}
	
	/**
	 * Retrieves the records in Analytics table with the given productId.
	 * METHOD = Get.
	 * 
	 * @param productId.
	 * @return List of all the records which contain that productId.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@GetMapping("/analytics/getById/{id}")
	public List<Analytics> getByProductId(@PathVariable(value = "id") int productId) throws ResourceNotFoundException {
		return this.analyticsService.getByProductId(productId);

	}
	
	/**
	 * Saves data in the Analytics table. METHOD = Post.
	 * 
	 * @return String for successful entry.
	 * @throws ResourceNotFoundException
	 *             if given Id does not exist
	 */
	@PostMapping("/analytics/save")
	public String postDataToAnalytics() {
		return this.analyticsService.postDataToAnalytics();
	}

}
