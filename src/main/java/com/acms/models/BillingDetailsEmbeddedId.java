package com.acms.models;

import java.io.Serializable;

import javax.persistence.Column;


public class BillingDetailsEmbeddedId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5911355292604736729L;
	@Column(name="billid")
	private int billId;
	@Column(name="productid")
	private String productId;
	public BillingDetailsEmbeddedId() {
		super();
	}
	public BillingDetailsEmbeddedId(int billId, String productId) {
		super();
		this.billId = billId;
		this.productId = productId;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
