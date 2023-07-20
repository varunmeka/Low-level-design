package service;

import Repository.ParkingLotRepository;
import Repository.ParkingTicketRepository;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleParkingLotService implements ParkingLotService {
    private ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
    private PricingService pricingService = new SimplePricingService();
    private ParkingTicketRepository parkingTicketRepository = new ParkingTicketRepository();

    @Override
    public ParkingTicket allocateParkingSpot(Vehicle vehicle){
        List<ParkingSpot> availableSpots = parkingLotRepository.getAvailableParkingSpots(vehicle.getVehicleType());
        if(availableSpots.size()==0)
            throw new RuntimeException("parking spots not avialable");
        ParkingSpot parkingSpot = availableSpots.get(0);
        parkingSpot.setAvailable(false);
        ParkingTicket parkingTicket = new ParkingTicket(LocalDateTime.now(),null,vehicle.getVehicleNo(),parkingSpot.getParkingSpotId(),0);
        parkingTicketRepository.addParkingticket(parkingTicket);
        vehicle.setParkingTicket(parkingTicket);
        return parkingTicket;
    }

    @Override
    public int exitParkingLot(Vehicle vehicle){
        LocalDateTime endTime = LocalDateTime.now();
        int fare = pricingService.getFare(vehicle.getVehicleType(),vehicle.getParkingTicket().getStartTime(),endTime);
        parkingLotRepository.updateParkingSpotStatus(vehicle.getParkingTicket().getParkingSpotId(),true);
        vehicle.getParkingTicket().setEndTime(endTime);
        vehicle.getParkingTicket().setFare(fare);
        return fare;
    }
}
