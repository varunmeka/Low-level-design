package model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Post {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int postId;
    private Product product;
    private Seller seller;
    private LocalDateTime postTime;

    public Post(Product product, Seller seller){
        this.postId = count.incrementAndGet();
        this.product = product;
        this.seller= seller;
        this.postTime = LocalDateTime.now();
    }
}
