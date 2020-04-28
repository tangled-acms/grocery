package com.acms.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;


@Entity
@Table(name="billingdetails")
public class BillingDetails {
	@EmbeddedId
	@JsonUnwrapped
	private BillingDetailsEmbeddedId billingDetailsEmbeddedId;
	private int quantity;
	@Column(name = "mrp")
	private double cost;
	
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
	public BillingDetailsEmbeddedId getBillingDetailsEmbeddedId() {
		return billingDetailsEmbeddedId;
	}
	public void setBillingDetailsEmbeddedId(BillingDetailsEmbeddedId billingDetailsEmbeddedId) {
		this.billingDetailsEmbeddedId = billingDetailsEmbeddedId;
	}
	
}
