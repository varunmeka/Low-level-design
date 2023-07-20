package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@ToString
public class Ride {
    private static final AtomicInteger count = new AtomicInteger(0);
    int rideId;
    Driver driver;
    User user;
    Location fromLocation;
    Location toLocation;
    double estimatedCost;
    RideStatus status;

    public Ride(Driver driver, User user,Location fromLocation,Location toLocation,double estimatedCost){
        this.rideId = count.incrementAndGet();
        this.driver = driver;
        this.user= user;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.estimatedCost = estimatedCost;
        this.status = RideStatus.ONGOING;
    }

}
