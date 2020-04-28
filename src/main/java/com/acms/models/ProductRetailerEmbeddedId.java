package com.acms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductRetailerEmbeddedId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "productid")
	private String productId;
	@Column(name = "retailerid")
	private String retailerId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	@Override
	public String toString() {
		return "ProductRetailerEmbeddedId [productId=" + productId + ", retailerId=" + retailerId + "]";
	}

}
