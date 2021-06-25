package ru.fomin.repositories;


import org.springframework.stereotype.Repository;
import ru.fomin.entities.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class UserRepo {
    private Map<Long, User> users = new HashMap<>() ;

    @PostConstruct
    public void init(){
        users.put(1L, new User(1L, "Tom"));
        users.put(2L, new User(2L, "Bob"));
        users.put(3L, new User(3L, "Mike"));
    }

    public User getById(Long id){
        return users.get(id);
    }

    public List<User> getAllUsers(){return new ArrayList<>(users.values());}

    public void update(User user) {
       users.put(user.getId(), user);
    }
}
