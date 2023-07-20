package repository;

import model.Driver;
import model.Ride;
import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class RideRepository {
    private static Map<Integer, Ride> rideMap = new HashMap<>();
    private static List<Ride> rides = new ArrayList<>();

    public void createRide(Ride ride){
        rideMap.put(ride.getRideId(),ride);
        rides.add(ride);
    }

    public List<Ride> getAllRides(){
        return rides;
    }

    public List<Ride> getRidesByUser(User user){
        return rides.stream().filter(ride -> {
            return ride.getUser().getUsername() ==user.getUsername();
        }).collect(Collectors.toList());
    }

    public List<Ride> getRidesByDriver(Driver driver){
        return rides.stream().filter(ride -> {
            return ride.getDriver().getName() == driver.getName();
        }).collect(Collectors.toList());
    }

    public Ride getRideById(int id){
        return rideMap.get(id);
    }
}
