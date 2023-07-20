package service;

import model.ParkingTicket;
import model.Vehicle;

public interface ParkingLotService {
    ParkingTicket allocateParkingSpot(Vehicle vehicle);
    int exitParkingLot(Vehicle vehicle);

}
