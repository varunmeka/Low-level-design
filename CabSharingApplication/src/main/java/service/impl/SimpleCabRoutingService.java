package service.impl;


import model.*;
import repository.DriverRepository;
import repository.RideRepository;
import service.CabRoutingService;
import service.PricingService;

import java.util.Collections;
import java.util.List;

public class SimpleCabRoutingService implements CabRoutingService {

    private DriverRepository driverRepository = new DriverRepository();
    private RideRepository rideRepository = new RideRepository();
    private PricingService pricingService = new TiredPricingService();

    @Override
    public Ride getRide(User user, Location fromLocation, Location toLocation) {
        List<Driver> drivers = driverRepository.getNearestAvailbleDrivers(fromLocation);
        Driver currDriver;
        if(drivers.size()==0)
            throw new RuntimeException("No drivers found");
        Collections.sort(drivers,(driver1,driver2)->{
            return  Double.compare(driver1.getCurrentLocation().getDistance(fromLocation), driver2.getCurrentLocation().getDistance(fromLocation));
        });
        currDriver = drivers.get(0);
        driverRepository.updateDriverAvailablity(currDriver.getName(),false);
        double estimatedCost = pricingService.caluclatePrice(fromLocation,toLocation);
        Ride ride = new Ride(currDriver,user,fromLocation,toLocation,estimatedCost);

        rideRepository.createRide(ride);
        return ride;
    }

    @Override
    public double completeRide(int rideId) {
        Ride ride = rideRepository.getRideById(rideId);
        ride.setStatus(RideStatus.COMPLETED);
        driverRepository.updateDriverAvailablity(ride.getDriver().getName(),true);
        driverRepository.updateLocation(ride.getDriver().getName(),ride.getToLocation());
        double price = pricingService.caluclatePrice(ride.getFromLocation(),ride.getToLocation());
        return price;
    }
}
