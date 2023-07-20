package Repository;

import model.ParkingSpot;
import model.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotRepository {
    private static List<ParkingSpot> parkingSpots = new ArrayList<>();

    public void addParkingSpot(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }

    public List<ParkingSpot> getAvailableParkingSpots(VehicleType vehicleType){
       return parkingSpots.stream().filter(parkingSpot -> {
            return parkingSpot.isAvailable() == true && parkingSpot.getVehicleType() == vehicleType;
        }).collect(Collectors.toList());
    }

    public void updateParkingSpotStatus(int parkingSpotId,boolean isAvialable){
        parkingSpots.forEach(parkingSpot -> {
            if(parkingSpot.getParkingSpotId() == parkingSpotId){
                parkingSpot.setAvailable(isAvialable);
            }
        });
    }
}
