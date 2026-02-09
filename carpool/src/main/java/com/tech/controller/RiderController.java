package com.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech.dto.Driver;
import com.tech.dto.RideRequest;
import com.tech.service.RiderSerivce;

@RestController
public class RiderController {

	@Autowired
	RiderSerivce riderSerivce;

	@PostMapping("/rider/request")
	public ResponseEntity<?> requestRide(@RequestBody RideRequest rideRequest) {
		Driver driver = riderSerivce.requestRide(rideRequest).orElse(null);
		return ResponseEntity.ok("Ride requested successfully"
				+ (driver != null ? ", Driver assigned: " + driver.getName() : ", No drivers available"));
	}
}
