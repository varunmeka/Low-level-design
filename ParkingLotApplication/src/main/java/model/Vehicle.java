package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Vehicle {
    String vehicleNo;
    VehicleType vehicleType;
    ParkingTicket parkingTicket;

    public Vehicle(String vehicleNo, VehicleType vehicleType){
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

}
