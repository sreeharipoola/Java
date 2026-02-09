package com.tech.service;


import java.util.Optional;

import com.tech.dto.Driver;
import com.tech.dto.RideRequest;

public interface RiderSerivce 
{
	public Optional<Driver> requestRide(RideRequest request);
}
