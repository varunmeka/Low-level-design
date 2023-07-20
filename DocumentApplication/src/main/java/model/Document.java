package model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Document {
    private static final AtomicInteger count = new AtomicInteger(0);
    int documentId;
    String name;
    String Content;
    Boolean isPrivate;
    User author;
    Map<AccessType,List<User>> accessUsers;

    public Document(String documentName,String content, User author){
        this.documentId = count.incrementAndGet();
        this.name = documentName;
        this.Content = content;
        this.author = author;
        this.isPrivate  = true;
        accessUsers = new HashMap<>();
    }
}
