package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class Location {
    int x;
    int y;

    public double getDistance(Location location){
        return Math.sqrt(Math.abs(location.x-x)*Math.abs(location.x-x)+Math.abs(location.y-y)*Math.abs(location.y-y));
    }

}
