package com.acms.models;

import java.time.Instant;

public class Billing {
	private int billId;
	private int timeStamp;
	private double subTotal;
	private String paymentMode;
	
	public Billing() {
		super();
		long epochTime = Instant.now().getEpochSecond();
		this.timeStamp = (int) epochTime;
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
