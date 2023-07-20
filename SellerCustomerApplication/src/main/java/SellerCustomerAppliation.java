import model.Customer;
import model.Seller;
import repository.CustomerRepository;
import repository.SellerRepository;
import service.PostService;

import java.util.stream.Collectors;

public class SellerCustomerAppliation {
    public static void main(String[] args){
        System.out.println("SellerCustomerAppliation Main Class");

        //create seller
        Seller seller1 = new Seller("Tony",4);
        Seller seller2 = new Seller("Thor",3);
        SellerRepository sellerRepository = new SellerRepository();
        sellerRepository.createSeller(seller1);
        sellerRepository.createSeller(seller2);
        System.out.println("All seller in the system: " +sellerRepository.getAllSellers().toString());
        //create Post
        PostService postService = new PostService();
        postService.createPost("One Plus 7 pro",50000,"Tony");
        postService.createPost("Macbook pro",200000,"Tony");
        postService.createPost("One Plus 7 pro",60000,"Thor");
        postService.createPost("Macbook pro",180000,"Thor");

        //create customer
        Customer customer1 = new Customer("Thanos");
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.create(customer1);

        //subscribe sellers
        customerRepository.addSubscribedSeller("Thanos",seller1);
        customerRepository.addSubscribedSeller("Thanos",seller2);
        System.out.println("customer subscribed to: "+customerRepository.getCustomerSubscribers("Thanos").toString());

        //get customer feed by time
        System.out.println("customer feed by time "+ postService.getCustomerFeedByTime("Thanos").stream().map(post-> post.getProduct().toString()).collect(Collectors.toList()).toString());

        //remove invalid post by seller
        try {
            postService.removePost("Thor", 6);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        //remove post by seller
        postService.removePost("Thor", 4);
        //get feed by ratings
        System.out.println("customer feed by Seller Rating "+ postService.getCustomerFeedBySellerRating("Thanos").stream().map(post-> post.getProduct().toString()).collect(Collectors.toList()).toString());

        //unsubscribe seller
        customerRepository.unSubscribeSeller("Thanos",seller1);
        //get feed by ratings
        System.out.println("customer feed by Seller Rating "+ postService.getCustomerFeedBySellerRating("Thanos").stream().map(post-> post.getProduct().toString()).collect(Collectors.toList()).toString());

    }
}
