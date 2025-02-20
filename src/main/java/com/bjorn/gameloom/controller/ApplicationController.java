package com.bjorn.gameloom.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/welcome")
        public String homePage(){
            return "forward:/index.html";
        }
    
   
}
