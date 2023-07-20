import Repository.ParkingLotRepository;
import Repository.ParkingTicketRepository;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;
import model.VehicleType;
import service.ParkingLotService;
import service.SimpleParkingLotService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotApplication {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ParkingLotApplication");
        // create parking spots
        ParkingSpot parkingSpot1 = new ParkingSpot(VehicleType.BIKE);
        ParkingSpot parkingSpot2 = new ParkingSpot(VehicleType.BIKE);
        ParkingSpot parkingSpot4 = new ParkingSpot(VehicleType.CAR);
        ParkingSpot parkingSpot5 = new ParkingSpot(VehicleType.CAR);
        ParkingSpot parkingSpot6 = new ParkingSpot(VehicleType.CAR);

        // add parking spots
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        parkingLotRepository.addParkingSpot(parkingSpot1);
        parkingLotRepository.addParkingSpot(parkingSpot2);
        parkingLotRepository.addParkingSpot(parkingSpot4);
        parkingLotRepository.addParkingSpot(parkingSpot5);
        parkingLotRepository.addParkingSpot(parkingSpot6);

        // create vehicle
        Vehicle vehicle = new Vehicle("ABC123",VehicleType.BIKE);
        ParkingLotService parkingLotService = new SimpleParkingLotService();
        ParkingTicket parkingTicket = parkingLotService.allocateParkingSpot(vehicle);

        System.out.println("assigned parking spot "+parkingTicket.toString());
        //Thread.sleep(5000);

        // reenter the vehicle
        Vehicle vehicle2 = new Vehicle("HGH123",VehicleType.BIKE);
        ParkingTicket parkingTicket2 = parkingLotService.allocateParkingSpot(vehicle2);
        System.out.println("assigned parking spot "+parkingTicket2.toString());

        //exit vehicle
       int fare = parkingLotService.exitParkingLot(vehicle);
        System.out.println("exited parking spot for vehicle "+fare);
//
        int fare2 = parkingLotService.exitParkingLot(vehicle);
       System.out.println("exited parking spot for vehicle "+fare2);
        // third bike
        Vehicle vehicle3 = new Vehicle("JKHK123",VehicleType.BIKE);
        ParkingTicket parkingTicket3 = parkingLotService.allocateParkingSpot(vehicle3);
        System.out.println("assigned parking spot "+parkingTicket3.toString());

        Vehicle vehicle4 = new Vehicle("HKKL123",VehicleType.BIKE);
        ParkingTicket parkingTicket4 = parkingLotService.allocateParkingSpot(vehicle4);
        System.out.println("assigned parking spot "+parkingTicket4.toString());
        Thread.sleep(2000);
        //exit vehicle
        int fare1 = parkingLotService.exitParkingLot(vehicle);
        System.out.println("exited parking spot for vehicle "+fare1);
        //get parking details by vehicle
        ParkingTicketRepository parkingTicketRepository = new ParkingTicketRepository();
        List<ParkingTicket> parkingTickets = parkingTicketRepository.getAllParkingTicketsByVehicle(vehicle.getVehicleNo());
        System.out.println(parkingTickets.stream().map(parkingTicket1->parkingTicket1.toString()).collect(Collectors.toList()).toString());

        // create van vehicle
        Vehicle vehicle1 = new Vehicle("KSHF-123",VehicleType.VAN);
        try {
            parkingLotService.allocateParkingSpot(vehicle1);
        }
        catch (Exception e){
            System.out.println("exception while assiging parking lot to "+vehicle1.getVehicleNo()+" message "+ e.getMessage());
        }
    }
}
