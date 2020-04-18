package com.acms.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="product")
public class Product {
	int serialNumber;
	@Id
	String productId;
	int timeStamp;
	String description;
	String name;
	double MRP;
	int quantity;
	double promotion;
	
	public Product(int serialNumber, String productId, int timeStamp, String description, String name, double mRP,
			int quantity, double promotion) {
		super();
		this.serialNumber = serialNumber;
		this.productId = productId;
		this.timeStamp = timeStamp;
		this.description = description;
		this.name = name;
		this.MRP = mRP;
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
	
	public int getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
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

	public double getMRP() {
		return MRP;
	}

	public void setMRP(double mRP) {
		MRP = mRP;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPromotion() {
		return promotion;
	}

	public void setPromotion(double promotion) {
		this.promotion = promotion;
	}
}
