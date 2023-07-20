package repository;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<String, User> userMap = new HashMap<>();

    public void createUser(User user){
        if(userMap.containsKey(user.getUsername())){
            throw new RuntimeException("user already exists");
        }
        userMap.put(user.getUsername(),user);
    }

}
