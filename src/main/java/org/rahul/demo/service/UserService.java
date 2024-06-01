package org.rahul.demo.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.rahul.demo.entity.User;
import org.rahul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

     public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
