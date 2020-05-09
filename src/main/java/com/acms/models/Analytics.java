package com.acms.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name = "analytics")
public class Analytics {
	@EmbeddedId
	@JsonUnwrapped
	private AnalyticsEmbeddedId analyticsEmbeddedId;
	private int sold;
	public AnalyticsEmbeddedId getAnalyticsEmbeddedId() {
		return analyticsEmbeddedId;
	}
	public void setAnalyticsEmbeddedId(AnalyticsEmbeddedId analyticsEmbeddedId) {
		this.analyticsEmbeddedId = analyticsEmbeddedId;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
}
