package org.rahul.demo.service;

import org.rahul.demo.entity.User;
import org.rahul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
            .password(user.getPassword())
            .roles(user.getRoles().toArray(new String[0]))
            .build();

            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username: "+username);
    }
    
}
