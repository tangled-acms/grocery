package com.acms.models;

import org.springframework.data.annotation.Id;

public class Retailer {
	int serialNumber;
	@Id
	String retailerId;
	String name;
	String address1;
	String address2;
	String address3;
	int contact1;
	int contact2;
	int contact3;
	
	public Retailer(int serialNumber, String retailerId, String name, String address1, String address2, String address3,
			int contact1, int contact2, int contact3) {
		super();
		this.serialNumber= serialNumber;
		this.retailerId = retailerId;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.contact1 = contact1;
		this.contact2 = contact2;
		this.contact3 = contact3;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int slNo) {
		this.serialNumber = slNo;
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
