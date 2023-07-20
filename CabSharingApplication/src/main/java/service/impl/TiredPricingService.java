package service.impl;

import model.Location;
import model.PricingTiers;
import service.PricingService;

public class TiredPricingService implements PricingService {
    private static final int minimumPrice = 50;

    @Override
    public double caluclatePrice(Location from, Location to){
        double distance =  to.getDistance(from);
        double price =1 ;
        while(distance>0){
            if(distance<= PricingTiers.Short.getValue()){
                price += 10.0;
                distance = distance-1.0;
            }
            else if(distance<= PricingTiers.Medium.getValue()){
                price += 8.0;
                distance = distance-1.0;
            }
            else if(distance < PricingTiers.Large.getValue()){
                price = price+ (distance-(double)PricingTiers.Medium.getValue()) * 5.0;
                distance = (double)PricingTiers.Medium.getValue();
            }
        }
        if(price<minimumPrice)
            price = minimumPrice;
        System.out.println("price for distance:"+to.getDistance(from)+" is "+price);
        return price;
    }

}
