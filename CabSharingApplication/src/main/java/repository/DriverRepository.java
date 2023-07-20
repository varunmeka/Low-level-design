package repository;

import model.Driver;
import model.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository {
    private static Map<String, Driver> driverMap = new HashMap<>();
    private static final int minimumRadius = 1000;

    public void createDriver(Driver driver){
        if(driverMap.containsKey(driver.getName())){
            throw new RuntimeException("driver already exists");
        }
        driverMap.put(driver.getName(),driver);
    }

    public void updateLocation(String driverName, Location location){
        if(!driverMap.containsKey(driverName)){
            throw new RuntimeException("driver does not exists");
        }
        driverMap.get(driverName).setCurrentLocation(location);
    }

    public List<Driver> getNearestAvailbleDrivers(Location location){
        List<Driver> drivers = new ArrayList<>();
        driverMap.values().forEach(driver -> {
            if(driver.getCurrentLocation().getDistance(location)<minimumRadius && driver.getIsAvailable()){
                drivers.add(driver);
            }
        });
        return drivers;
    }

    public void updateDriverAvailablity(String driverName,boolean isAvailable){
        driverMap.get(driverName).setIsAvailable(isAvailable);
    }
}
