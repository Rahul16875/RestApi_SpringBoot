package org.rahul.demo.controller;

import org.rahul.demo.entity.User;
import org.rahul.demo.repository.UserRepository;
import org.rahul.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired 
    private UserRepository userRepository;

    // @GetMapping
    // public List<User> getAllUser(){
    //     return userService.getAll();
    // }

    // @PostMapping
    // public void createUser(@RequestBody User user){
    //     userService.saveEntry(user);
    // }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

         User userInDb =  userService.findByUserName(userName);

            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}
