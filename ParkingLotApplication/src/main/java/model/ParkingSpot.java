package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@ToString
public class ParkingSpot {
    private static final AtomicInteger count = new AtomicInteger();
    int parkingSpotId;
    VehicleType vehicleType;
    boolean isAvailable;

    public ParkingSpot(VehicleType vehicleType){
        this.parkingSpotId = count.incrementAndGet();
        this.vehicleType = vehicleType;
        isAvailable = true;
    }
}
