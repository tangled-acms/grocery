package com.acms.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billing")
public class Billing {
	@Id
	@Column(name = "billid")
	private int billId;
	@Column(name = "timestamp")
	private long timeStamp;
	@Column(name = "subtotal")
	private double subTotal;
	@Column(name = "paymentmode")
	private String paymentMode;
	
	public Billing() {
		super();
		long epochTime = Instant.now().getEpochSecond();
		this.timeStamp = epochTime;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
}
