package com.acms.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name = "productdetails")
public class ProductRetailer {
	@EmbeddedId
	@JsonUnwrapped
	private ProductRetailerEmbeddedId productRetailerEmbeddedId;
	@Column(name = "timestamp")
	private long timeStamp;
	private int quantity;
	@Column(name = "mrp")
	private double cost;

	public ProductRetailer() {
		super();
		long epochTime = Instant.now().getEpochSecond();
		this.timeStamp = epochTime;
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

	public ProductRetailerEmbeddedId getProductRetailerEmbeddedId() {
		return productRetailerEmbeddedId;
	}

	public void setProductRetailerEmbeddedId(ProductRetailerEmbeddedId productRetailerEmbeddedId) {
		this.productRetailerEmbeddedId = productRetailerEmbeddedId;
	}

}
