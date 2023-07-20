package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Driver {
    String name;
    CabType cabType;
    //String licenseNO;
    Location currentLocation;
    Boolean isAvailable;
}
