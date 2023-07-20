import model.*;
import repository.DriverRepository;
import repository.RideRepository;
import repository.UserRepository;
import service.CabRoutingService;
import service.impl.SimpleCabRoutingService;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class CabSharingApplication {
    public static void main(String[] args){
        System.out.println("CabSharingApplication started at: "+ LocalDateTime.now());
        //creating users
        UserRepository userRepository = new UserRepository();
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        userRepository.createUser(user1);
        userRepository.createUser(user2);
        userRepository.createUser(user3);

        //Creating Riders
        DriverRepository driverRepository = new DriverRepository();
        Driver driver1 = new Driver("driver1", CabType.MINI,new Location(1,2),true);
        Driver driver2 = new Driver("driver2", CabType.MINI,new Location(3,5),true);
        Driver driver3 = new Driver("driver3", CabType.MINI,new Location(10,12),true);
        Driver driver4 = new Driver("driver4", CabType.MINI,new Location(15,21),true);
        driverRepository.createDriver(driver1);
        driverRepository.createDriver(driver2);
        driverRepository.createDriver(driver3);
        driverRepository.createDriver(driver4);

        //Creating ride
        CabRoutingService cabRoutingService = new SimpleCabRoutingService();
        RideRepository rideRepository = new RideRepository();
        Ride ride1 = cabRoutingService.getRide(user1,new Location(0,1),new Location(3,4));
        System.out.println("created ride1 : "+ ride1.toString());
        try {
            Ride ride2 = cabRoutingService.getRide(user2, new Location(100, 15), new Location(3, 4));
            System.out.println("created ride2 : "+ ride2.toString());
        }
        catch (Exception e){
            System.out.println("got exception for"+e.getMessage());
        }



        System.out.println("rides for user1:"+rideRepository.getRidesByUser(user1).stream().map(ride -> ride.toString()).collect(Collectors.toList()).toString());
        double price = cabRoutingService.completeRide(ride1.getRideId());
        //System.out.println("cost for ride"+ ride1.getRideId()+" "+price);
        System.out.println("rides for user1:"+rideRepository.getRidesByUser(user1).stream().map(ride -> ride.toString()).collect(Collectors.toList()).toString());
    }

}

// TODO
// exception handling
// locking for rides
//
