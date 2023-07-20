package repository;

import model.Seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SellerRepository {
    private static Map<String, Seller> sellerMap = new HashMap<>();

    public void createSeller(Seller seller){
        sellerMap.put(seller.getSellerName(),seller);
    }

    public List<String> getAllSellers(){
        return sellerMap.values().stream().map(Seller::getSellerName).collect(Collectors.toList());
    }

    public Seller getSellerByName(String name){
        return sellerMap.get(name);
    }
}
