package com.tech.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

import com.tech.dto.Driver;
import com.tech.dto.RideRequest;
import com.tech.service.RiderSerivce;
 
@Service
public class RiderServiceImpl implements RiderSerivce {
 
    // Use a thread-safe map to store drivers and their availability status
    // Key: Driver ID, Value: Is available (true/false)
    private final ConcurrentHashMap<Long, AtomicBoolean> driverAvailability = new ConcurrentHashMap<>();
    
    // In-memory list of all available drivers (assuming pre-loaded data)
    private final List<Driver> availableDrivers = List.of(
        new Driver(1L, "Driver1","Ka001",13.0049, 77.69211), // Bangalore lat/lon example
        new Driver(2L, "Driver2","KA02",13.0519,77.7427));
        // ... more drivers
 
 
    @Override
    public Optional<Driver> requestRide(RideRequest request) {
        // 1. Calculate distance to all available drivers
        
    	
    	Optional<Driver> closestDriver = availableDrivers.stream()
            .filter(driver -> driverAvailability.getOrDefault(driver.getId(), new AtomicBoolean(true)).get())
            .min(Comparator.comparingDouble(driver ->
                calculateHaversineDistance(request.getCustLatitude(), request.getCustLongitude(),
                                           driver.getLatitude(), driver.getLongitude())
            ));
 
        // 2. Assign the driver using thread-safe method
        closestDriver.ifPresent(driver -> {
            // Attempt to mark the driver as unavailable atomically
            driverAvailability.compute(driver.getId(), (id, availability) -> {
                if (availability == null || availability.get()) {
                    return new AtomicBoolean(false); // Mark as busy
                }
                return availability; // Already busy, should not happen with the filter above
            });
        });
        
        return closestDriver;
    }
 
    // Haversine formula helper function
    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

