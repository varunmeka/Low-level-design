package Repository;

import model.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleRepository {
    private static Map<String, Vehicle> vehicleMap = new HashMap<>();


    public void addVehicle(Vehicle vehicle){
        vehicleMap.put(vehicle.getVehicleNo(),vehicle);
    }

    public Vehicle getVehicleByNo(String vehicleNo){
        if(vehicleMap.containsKey(vehicleNo)){
            return vehicleMap.get(vehicleNo);
        }
        return null;
    }
}
