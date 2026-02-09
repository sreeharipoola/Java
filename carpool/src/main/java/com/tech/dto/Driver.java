package com.tech.dto;

public class Driver {
	private Long id;
	private String name;
	private String vehicleNumber;
	private double latitude;
	private double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Driver(Long id, String name, String vehicleNumber, double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.vehicleNumber = vehicleNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// Getters and Setters...
}
