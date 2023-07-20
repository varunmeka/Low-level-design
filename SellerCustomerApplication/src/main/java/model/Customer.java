package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Customer {
    private String customerName;
    private List<Seller> subscribedSeller;

    public Customer(String customerName){
        this.customerName = customerName;
        subscribedSeller = new ArrayList<>();
    }

}
