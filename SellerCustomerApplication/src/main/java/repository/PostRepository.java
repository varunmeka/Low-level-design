package repository;

import model.Post;
import model.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostRepository {
    private static Map<Integer, Post> postMap = new HashMap<>();
    private static List<Post> posts = new ArrayList<>();

    public void createPost(Post post){
        postMap.put(post.getPostId(),post);
        posts.add(post);
    }

    public List<Post> getPostsBySeller(Seller seller){
        return posts.stream()
                .filter(post1 -> post1.getSeller().getSellerName() == seller.getSellerName())
                .collect(Collectors.toList());
    }

    public void deletePost(String sellerName,int postId){
        if(postId>posts.size()){
            throw new RuntimeException("invalid post id");
        }
        if(postMap.get(postId).getSeller().getSellerName()!= sellerName){
            throw new RuntimeException("Seller cannot remove post");
        }
        postMap.remove(postId);
        posts.removeIf(post -> {
            return post.getPostId()==postId;
        });
    }
}
