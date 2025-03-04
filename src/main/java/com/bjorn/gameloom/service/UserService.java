package com.bjorn.gameloom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bjorn.gameloom.model.User;
import com.bjorn.gameloom.repository.UserRepository;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')") 
    // public void deleteUser(Long userId);
    
    /*
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // Specify the required password here
    String password = "password@123"; 
    String encodedPassword = passwordEncoder.encode(password);
     */
}
