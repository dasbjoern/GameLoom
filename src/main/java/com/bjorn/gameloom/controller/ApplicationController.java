package com.bjorn.gameloom.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/welcome")
        public String homePage(){
            return "forward:/index.html";
        }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/search")
        public String searchPage(){
            return "forward:/index.html";
            }
    
    @PostMapping("/welcome")
    public String searchSteam(){

        return "forward:/index.html";
    }
   
}
