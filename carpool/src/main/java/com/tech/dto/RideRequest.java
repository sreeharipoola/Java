package com.tech.dto;

public class RideRequest {
	private int customerId;
	private double custLatitude;
	private double custLongitude;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getCustLatitude() {
		return custLatitude;
	}

	public void setCustLatitude(double custLatitude) {
		this.custLatitude = custLatitude;
	}

	public double getCustLongitude() {
		return custLongitude;
	}

	public void setCustLongitude(double custLongitude) {
		this.custLongitude = custLongitude;
	}

}
