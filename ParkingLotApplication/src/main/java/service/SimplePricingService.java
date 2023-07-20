package service;

import model.VehicleType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SimplePricingService implements PricingService{

    @Override
    public int getFare(int parkingLotId,VehicleType vehicleType, LocalDateTime startTime, LocalDateTime endTime) {

        switch parkingLotId:

        long timeDiff = getDuration(startTime,endTime);
        if(timeDiff>4){
            return 60;
        }
        if(timeDiff>=2)
            return 40;
        return 20;
    }

    private long getDuration(LocalDateTime startTime, LocalDateTime endTime){
       return startTime.until(endTime, ChronoUnit.SECONDS);
    }
}
