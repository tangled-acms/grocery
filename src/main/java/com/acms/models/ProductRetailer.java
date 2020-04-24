package com.acms.models;

import java.time.Instant;

public class ProductRetailer {
	private String productId;
	private String retailerId;
	private int timeStamp;
	private int quantity;
	private double cost;

	public ProductRetailer() {
		super();
		long epochTime = Instant.now().getEpochSecond();
		this.timeStamp = (int) epochTime;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
