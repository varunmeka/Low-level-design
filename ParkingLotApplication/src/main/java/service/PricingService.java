package service;

import model.Vehicle;
import model.VehicleType;

import java.time.LocalDateTime;

public interface PricingService {

    int getFare(VehicleType vehicleType, LocalDateTime startTime, LocalDateTime endTime);
}
