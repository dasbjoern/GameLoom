package com.bjorn.gameloom.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bjorn.gameloom.model.User;
import com.bjorn.gameloom.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    // @Autowired
    private final UserRepository userRepository;
    
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <User> user = userRepository.findByUsername(username);
        

        if(user.isPresent()){
            // System.out.println("\n" + user.get().getRole().toString().toUpperCase() + "\n");
            BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
    	    String encodedPass = bEncoder.encode(user.get().getPassword());

            UserDetails usr = org.springframework.security.core.userdetails.User.builder()
            .username(user.get().getUsername())//user.get().getUsername())
            .password(encodedPass)
            .roles(user.get().getRole().toString().toUpperCase())
            .build();
            
            return usr;


        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

}