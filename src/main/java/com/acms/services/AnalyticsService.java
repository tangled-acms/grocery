package com.acms.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
	public List<Analytics> getByProductId(String productId) {
		List<Analytics> analytics = this.analyticsRepository.findByAnalyticsEmbeddedIdProductId(productId);
		return analytics;

	}

	/**
	 * Function to save the HashMap to Analytics Table
	 * 
	 * @return String for successful entry
	 */
	public String postDataToAnalytics() {
		for (String i : map.keySet()) {
			int sold = map.get(i);

			AnalyticsEmbeddedId analyticsId = new AnalyticsEmbeddedId();
			analyticsId.setProductId(i);
			analyticsId.setTimeStamp(Instant.now().getEpochSecond());

			Analytics analytics = new Analytics();
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

	/**
	 * Function to display output of python scrpit
	 * 
	 * @return string of the output
	 */
	public String pythonScriptOutput() {
		String finalStr = "";
		Process process = null;
		try {
			process = Runtime.getRuntime()
					.exec("python C:\\Users\\madhu\\PycharmProjects\\SentimentAnalysiss\\example.py 1 2");
		} catch (Exception e1) {
			System.out.println("Exception Raised" + e1.toString());
		}
		InputStream stdout = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				finalStr = finalStr + line + "\n";
			}
		} catch (IOException e) {
			System.out.println("Exception in reading output" + e.toString());
		}
		return finalStr;
	}

}
