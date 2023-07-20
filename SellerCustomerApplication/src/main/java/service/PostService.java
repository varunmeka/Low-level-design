package service;

import model.Customer;
import model.Post;
import model.Product;
import model.Seller;
import repository.CustomerRepository;
import repository.PostRepository;
import repository.SellerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostService {
    private PostRepository postRepository= new PostRepository();
    private SellerRepository sellerRepository = new SellerRepository();
    private CustomerRepository customerRepository = new CustomerRepository();

    public int createPost(String productName,int price,String sellerName){
        Seller seller = sellerRepository.getSellerByName(sellerName);
        Product product = new Product(productName,price);
        Post post = new Post(product,seller);
        postRepository.createPost(post);
        return post.getPostId();
    }

    public void removePost(String sellerName,int postId){
        postRepository.deletePost(sellerName, postId);
    }
    public List<Post> getCustomerFeedByTime(String customerName){
        List<Post> feed = getCustomerFeed(customerName);
        Collections.sort(feed,((post1 ,post2)->post1.getPostTime().compareTo(post2.getPostTime())));
        return feed;
    }

    public List<Post> getCustomerFeedBySellerRating(String customerName){
        List<Post> feed = getCustomerFeed(customerName);
        Collections.sort(feed,((post1 ,post2)->
                post1.getSeller().getRating() - post2.getSeller().getRating()));
        return feed;
    }

    private List<Post> getCustomerFeed(String customerName){
        Customer customer = customerRepository.getCustomerByName(customerName);
        List<Post> feed = new ArrayList<>();
        customer.getSubscribedSeller().forEach(seller -> {
            List<Post> sellerPosts = postRepository.getPostsBySeller(seller);
            feed.addAll(sellerPosts);
        });
        return feed;
    }
}
