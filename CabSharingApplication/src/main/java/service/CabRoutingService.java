package service;

import model.Location;
import model.Ride;
import model.User;

import java.util.List;

public interface CabRoutingService {
    Ride getRide(User user, Location fromLocation, Location toLocation);
    double completeRide(int rideId);
}
