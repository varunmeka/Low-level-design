package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class User {
    private String userName;

    public User(String userName){
        this.userName = userName;
    }
}
