package com.acms.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "productid")
	private String productId;
	@Column(name = "timestamp")
	private int timeStamp;
	private String description;
	private String name;
	@Column(name = "mrp")
	private double MRP;
	private int quantity;
	private double promotion;
	
	public Product() {
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

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", timeStamp=" + timeStamp + ", description=" + description
				+ ", name=" + name + ", MRP=" + MRP + ", quantity=" + quantity + ", promotion=" + promotion + "]";
	}
}
