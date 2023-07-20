package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ParkingTicket {
    private LocalDateTime startTime;
    private  LocalDateTime endTime;
    private String vehicleNo;
    private int parkingSpotId;
    private int fare;
}
