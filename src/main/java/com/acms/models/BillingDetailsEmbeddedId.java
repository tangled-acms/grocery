package com.acms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillingDetailsEmbeddedId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "billid")
	private int billId;
	@Column(name = "productid")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + billId;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingDetailsEmbeddedId other = (BillingDetailsEmbeddedId) obj;
		if (billId != other.billId)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillingDetailsEmbeddedId [billId=" + billId + ", productId=" + productId + "]";
	}

}
