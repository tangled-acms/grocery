package com.acms.models;

import org.springframework.data.annotation.Id;

public class Product {
	int serialNumber;
	@Id
	String productId;
	String description;
	String name;
	float MRP;
	int quantity;
	float promotion;
	
	public Product(int serialNumber, String productId, String description, String name, float mRP, int quantity,
			float promotion) {
		super();
		this.serialNumber = serialNumber;
		this.productId = productId;
		this.description = description;
		this.name = name;
		MRP = mRP;
		this.quantity = quantity;
		this.promotion = promotion;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMRP() {
		return MRP;
	}

	public void setMRP(float mRP) {
		MRP = mRP;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPromotion() {
		return promotion;
	}

	public void setPromotion(float promotion) {
		this.promotion = promotion;
	}
}
