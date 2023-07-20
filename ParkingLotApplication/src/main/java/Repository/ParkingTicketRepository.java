package Repository;

import model.ParkingTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingTicketRepository {
    private static List<ParkingTicket> parkingTickets = new ArrayList<>();

    public void addParkingticket(ParkingTicket parkingTicket){
        parkingTickets.add(parkingTicket);
    }

    public List<ParkingTicket> getAllParkingTicketsByVehicle(String VehicleNo){
        return parkingTickets.stream().filter(parkingTicket -> {
            return parkingTicket.getVehicleNo() == VehicleNo;
        }).collect(Collectors.toList());
    }
}
