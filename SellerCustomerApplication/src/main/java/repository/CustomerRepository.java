package repository;

import model.Customer;
import model.Seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerRepository {
    private static Map<String, Customer> customerMap = new HashMap<>();

    public void create(Customer customer){
        customerMap.put(customer.getCustomerName(),customer);
    }

    public void addSubscribedSeller(String customerName,Seller seller){
        customerMap.get(customerName).getSubscribedSeller().add(seller);
    }

    public void unSubscribeSeller(String customerName,Seller seller){
        customerMap.get(customerName).getSubscribedSeller().remove(seller);
    }

    public Customer getCustomerByName(String name){
        return customerMap.get(name);
    }
    public List<String> getCustomerSubscribers(String customerName){
        return customerMap.get(customerName).getSubscribedSeller()
                .stream()
                .map(Seller::getSellerName).collect(Collectors.toList());
    }
}
