package com.acms.models;

import java.time.Instant;

import org.springframework.data.annotation.Id;

public class Retailer {
	@Id
	private String retailerId;
	private int timeStamp;
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private int contact1;
	private int contact2;
	private int contact3;
	
	public Retailer() {
		super();
		long epochTime = Instant.now().getEpochSecond();
		this.timeStamp = (int) epochTime;
	}
	
	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public int getContact1() {
		return contact1;
	}

	public void setContact1(int contact1) {
		this.contact1 = contact1;
	}

	public int getContact2() {
		return contact2;
	}

	public void setContact2(int contact2) {
		this.contact2 = contact2;
	}

	public int getContact3() {
		return contact3;
	}

	public void setContact3(int contact3) {
		this.contact3 = contact3;
	}
}
