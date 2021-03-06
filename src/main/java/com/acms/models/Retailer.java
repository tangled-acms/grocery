package com.acms.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="retailer")
public class Retailer {
	@Id
	@Column(name = "retailerid")
	private String retailerId;
	@Column(name = "timestamp")
	private long timeStamp;
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private int contact1;
	private int contact2;
	private int contact3;
	private int activestatus;
	
	public Retailer() {
		super();
		long epochTime = Instant.now().getEpochSecond();
		this.timeStamp = epochTime;
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

	public int getActivestatus() {
		return activestatus;
	}

	public void setActivestatus(int activestatus) {
		this.activestatus = activestatus;
	}
	
}
