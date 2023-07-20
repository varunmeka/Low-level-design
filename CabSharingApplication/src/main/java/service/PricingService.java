package service;

import model.Location;

public interface PricingService {
    double caluclatePrice(Location from,Location to);
}
